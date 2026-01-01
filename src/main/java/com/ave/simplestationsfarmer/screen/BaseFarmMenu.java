package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationscore.screen.DataSlotHelper;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class BaseFarmMenu extends BaseStationMenu {
    public BaseFarmMenu(int containerId, Inventory inventory, FriendlyByteBuf data, MenuType<?> menu) {
        super(containerId, inventory, data, menu);
    }

    public BaseFarmMenu(int containerId, Inventory inventory, StationContainer be, MenuType<?> menu) {
        super(containerId, inventory, be, menu);
    }

    @Override
    public void addItemSlots() {
        addItemSlot(blockEntity.inventory, BaseFarmerBlockEntity.OUTPUT_SLOT, UIBlocks.OUT_SLOT);
        addItemSlot(blockEntity.inventory, BaseFarmerBlockEntity.FLUID_SLOT, UIBlocks.WATER_SLOT);
        addItemSlot(blockEntity.inventory, BaseFarmerBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT);
        addItemSlot(blockEntity.inventory, BaseFarmerBlockEntity.FERTI_SLOT, UIBlocks.FERTI_SLOT);
        addItemSlot(blockEntity.inventory, BaseFarmerBlockEntity.FUEL_SLOT, UIBlocks.RED_SLOT);
    }

    @Override
    public void addDataSlots(BaseStationBlockEntity station) {
        super.addDataSlots(station);
        var farm = (BaseFarmerBlockEntity) station;
        addDataSlot(DataSlotHelper.fromInt(() -> farm.getFertResource().get(), x -> farm.getFertResource().set(x)));
        addDataSlot(DataSlotHelper.fromInt(() -> farm.getFluidResource().get(), x -> farm.getFluidResource().set(x)));
    }
}
