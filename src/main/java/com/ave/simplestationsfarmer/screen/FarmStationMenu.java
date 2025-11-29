package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationsfarmer.blockentity.ModContainer;
import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class FarmStationMenu extends BaseStationMenu {
    public FarmStationMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.FARM_MENU.get());
    }

    public FarmStationMenu(int containerId, Inventory inventory, ModContainer be) {
        super(containerId, inventory, be, ModMenuTypes.FARM_MENU.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.FARMER_BLOCK.get());
    }
}
