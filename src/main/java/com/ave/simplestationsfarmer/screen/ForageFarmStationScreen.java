package com.ave.simplestationsfarmer.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ForageFarmStationScreen extends BaseStationScreen {

    public ForageFarmStationScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsfarmer.forage_farmer");
    }

    @Override
    public int getFertColor() {
        return 0xAAEEFFFF;
    }
}
