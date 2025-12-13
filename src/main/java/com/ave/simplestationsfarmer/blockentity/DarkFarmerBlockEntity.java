package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.screen.DarkFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class DarkFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int LavaUsage = Config.WATER_PER_CYCLE.get() / 100;

    public DarkFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DARK_FARMER_ENTITY.get(), pos, state, CropGroup.Dark);

        powerUsage = 3;
        fluidUsage = LavaUsage;
    }

    @Override
    public DarkFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new DarkFarmStationMenu(containerId, inventory, this);
    }

    @Override
    protected SoundEvent getSound() {
        return SoundEvents.WART_BLOCK_BREAK;
    }
}
