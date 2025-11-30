package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationsfarmer.blockentity.ModContainer;
import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ForageFarmStationMenu extends BaseStationMenu {
    public ForageFarmStationMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.FORAGE_FARM_MENU.get());
    }

    public ForageFarmStationMenu(int containerId, Inventory inventory, ModContainer be) {
        super(containerId, inventory, be, ModMenuTypes.FORAGE_FARM_MENU.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.FORAGE_FARMER_BLOCK.get());
    }

}
