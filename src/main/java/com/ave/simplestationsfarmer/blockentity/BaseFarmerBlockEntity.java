package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationscore.resources.StationResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;
import com.ave.simplestationsfarmer.blockentity.handlers.FarmItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalFluidItemResource;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public abstract class BaseFarmerBlockEntity extends BaseStationBlockEntity {
    public static final int FLUID_SLOT = 2;
    public static final int TYPE_SLOT = 3;
    public static final int FERTI_SLOT = 4;

    public CropType type = CropType.Unknown;

    public BaseFarmerBlockEntity(BlockEntityType entity, BlockPos pos, BlockState state, CropGroup group) {
        super(entity, pos, state);

        resources.put(FERTI_SLOT,
                new OptionalFluidItemResource(Config.FERT_MAX.get(), 1, Config.FERT_PER_ITEM.get(), "fertilizer"));

        inventory = new FarmItemHandler(5, group) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override()
    protected void preWorkTick() {
        if (getFertResource().isEnough())
            progress += Config.FERT_MULT.get();
        if (getEnergyResource().isEnough())
            progress += Config.POWER_MULT.get();
    }

    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        if (stack.isEmpty())
            return -1;
        var type = CropType.findBySeed(stack.getItem());
        return type.equals(CropType.Unknown) ? -1 : type.ordinal();
    }

    public int getMaxProgress() {
        return Config.MAX_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct(boolean check) {
        var type = getCurrentType();
        if (type < 0)
            return ItemStack.EMPTY;
        var cropType = CropType.findById(type);
        if (check && cropType.product == null && !inventory.getStackInSlot(OUTPUT_SLOT).isEmpty())
            return ItemStack.EMPTY;
        return cropType.getProduct();
    }

    public StationResource getFertResource() {
        return resources.get(FERTI_SLOT);
    }

    public StationResource getFluidResource() {
        return resources.get(FLUID_SLOT);
    }

    @Override()
    public FluidTank getWaterStorage() {
        var resource = resources.get(FLUID_SLOT);
        if (resource instanceof FluidResource res)
            return res.storage;
        return null;
    }
}
