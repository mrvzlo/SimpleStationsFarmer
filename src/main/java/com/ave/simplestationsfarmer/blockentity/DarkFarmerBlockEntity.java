package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalEnergyResource;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.DarkFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class DarkFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int LavaUsage = Config.WATER_PER_CYCLE.get() / 100;

    public DarkFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.DARK_FARMER.getEntity(), pos, state, CropGroup.Dark);

        resources.put(FUEL_SLOT, new OptionalEnergyResource(3));
        resources.put(FLUID_SLOT, new FluidResource(Fluids.LAVA, Config.FLUID_MAX.get(), LavaUsage));
    }

    @Override
    public DarkFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new DarkFarmStationMenu(containerId, inventory, this);
    }

    public SoundEvent getWorkSound() {
        return SoundEvents.WART_BLOCK_BREAK;
    }
}
