package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationsfarmer.blockentity.ModContainer;
import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class TreeFarmStationMenu extends BaseStationMenu {
    public TreeFarmStationMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.TREE_FARM_MENU.get());
    }

    public TreeFarmStationMenu(int containerId, Inventory inventory, ModContainer be) {
        super(containerId, inventory, be, ModMenuTypes.TREE_FARM_MENU.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.TREE_FARMER_BLOCK.get());
    }

}
