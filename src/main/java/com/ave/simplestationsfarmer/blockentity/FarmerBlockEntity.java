package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.screen.FarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class FarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get();

    public FarmerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FARMER_ENTITY.get(), pos, state, CropGroup.Crop);

        powerUsage = 2;
        fluidUsage = WaterUsage;
    }

    @Override
    public FarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FarmStationMenu(containerId, inventory, this);
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.FARMER_ENTITY.get(),
                (be, d) -> be.getInventory(d));
    }
}
