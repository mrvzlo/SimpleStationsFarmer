package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationscore.screen.BaseStationMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class FarmStationScreen extends BaseFarmScreen {

    public FarmStationScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsfarmer.farmer");
    }

    @Override
    public int getFertColor() {
        return 0xAAEEFFFF;
    }
}
