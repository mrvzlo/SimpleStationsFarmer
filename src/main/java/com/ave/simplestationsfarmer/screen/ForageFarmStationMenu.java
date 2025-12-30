package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ForageFarmStationMenu extends BaseFarmMenu {
    public ForageFarmStationMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, Registrations.FORAGE_FARM_MENU.get());
    }

    public ForageFarmStationMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, Registrations.FORAGE_FARM_MENU.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.FORAGE_FARMER.getBlock());
    }

}
