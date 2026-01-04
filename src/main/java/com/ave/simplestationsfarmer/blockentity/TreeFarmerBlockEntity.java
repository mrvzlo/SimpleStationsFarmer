package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.TreeType;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalEnergyResource;
import com.ave.simplestationsfarmer.blockentity.handlers.TreeFarmItemHandler;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.TreeFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class TreeFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get() * 2;

    public TreeFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.TREE_FARMER.getEntity(), pos, state);

        resources.put(FUEL_SLOT, new OptionalEnergyResource(6));
        resources.put(FLUID_SLOT, new FluidResource(Fluids.WATER, Config.FLUID_MAX.get(), WaterUsage));

        inventory = new TreeFarmItemHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public TreeFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TreeFarmStationMenu(containerId, inventory, this);
    }

    public SoundEvent getWorkSound() {
        return SoundEvents.WOOD_BREAK;
    }

    protected int getTypeBySeed(Item item) {
        var type = TreeType.findBySeed(item);
        return type == null ? -1 : type.ordinal();
    }

    public ItemStack getStackByType(int type) {
        return TreeType.findById(type).getProduct();
    }
}
