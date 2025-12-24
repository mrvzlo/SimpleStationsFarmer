package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.screen.ForageFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ForageFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get() / 10;

    public ForageFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORAGE_FARMER_ENTITY.get(), pos, state, CropGroup.Forage);

        powerUsage = 1;
        fluidUsage = WaterUsage;
        speed = 10;
    }

    @Override
    public ForageFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ForageFarmStationMenu(containerId, inventory, this);
    }

    @Override
    protected SoundEvent getSound() {
        return SoundEvents.FLOWERING_AZALEA_BREAK;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.FORAGE_FARMER_ENTITY.get(),
                (be, d) -> be.getInventory(d));
    }
}
