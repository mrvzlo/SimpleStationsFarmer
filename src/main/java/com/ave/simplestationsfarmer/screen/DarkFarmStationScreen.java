package com.ave.simplestationsfarmer.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DarkFarmStationScreen extends BaseStationScreen {

    public DarkFarmStationScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsfarmer.dark_farmer");
    }

    @Override
    public int getFertColor() {
        return 0xAA6E4C1B;
    }
}
