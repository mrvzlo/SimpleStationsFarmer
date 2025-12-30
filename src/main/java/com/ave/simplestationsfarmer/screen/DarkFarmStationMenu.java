package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class DarkFarmStationMenu extends BaseFarmMenu {
    public DarkFarmStationMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, Registrations.DARK_FARM_MENU.get());
    }

    public DarkFarmStationMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, Registrations.DARK_FARM_MENU.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.DARK_FARMER.getBlock());
    }

}
