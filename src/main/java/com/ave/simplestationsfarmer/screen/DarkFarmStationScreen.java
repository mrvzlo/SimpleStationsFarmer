package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationscore.screen.BaseStationMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DarkFarmStationScreen extends BaseFarmScreen {

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

    @Override
    public int getFluidColor() {
        return 0xAAea5c0f;
    }

    @Override
    protected Component getFluidName() {
        return Component.translatable("screen.simplestationsfarmer.lava");
    }
}
