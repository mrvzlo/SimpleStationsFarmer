package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister
            .create(net.minecraft.core.registries.Registries.MENU, SimpleStationsFarmer.MODID);

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

    public static final DeferredHolder<MenuType<?>, MenuType<StationMenu>> MINER_MENU = registerMenuType(
            "miner_menu", StationMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
}
