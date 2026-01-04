package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationscore.resources.StationResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalFluidItemResource;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public abstract class BaseFarmerBlockEntity extends BaseStationBlockEntity {
    public static final int FLUID_SLOT = 2;
    public static final int TYPE_SLOT = 3;
    public static final int FERTI_SLOT = 4;

    public BaseFarmerBlockEntity(BlockEntityType entity, BlockPos pos, BlockState state) {
        super(entity, pos, state);

        resources.put(FERTI_SLOT,
                new OptionalFluidItemResource(Config.FERT_MAX.get(), 1, Config.FERT_PER_ITEM.get(), "fertilizer"));
    }

    @Override()
    protected void preWorkTick() {
        if (getFertResource().isEnough())
            progress += Config.FERT_MULT.get();
        if (getEnergyResource().isEnough())
            progress += Config.POWER_MULT.get();
    }

    public int getMaxProgress() {
        return Config.MAX_PROGRESS.getAsInt();
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

    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        if (stack.isEmpty())
            return -1;
        return getTypeBySeed(stack.getItem());
    }

    @Override
    public ItemStack getProduct(boolean check) {
        var type = getCurrentType();
        if (type == -1)
            return ItemStack.EMPTY;
        return getStackByType(type);
    }

    protected abstract int getTypeBySeed(Item item);

    protected abstract ItemStack getStackByType(int type);
}
