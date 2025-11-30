package com.ave.simplestationsfarmer.screen;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
        public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister
                        .create(net.minecraft.core.registries.Registries.MENU, SimpleStationsFarmer.MODID);

        public static void register(IEventBus eventBus) {
                MENUS.register(eventBus);
        }

        public static final RegistryObject<MenuType<FarmStationMenu>> FARM_MENU = MENUS.register("farm_menu",
                        () -> IForgeMenuType.create(FarmStationMenu::new));

        public static final RegistryObject<MenuType<DarkFarmStationMenu>> DARK_FARM_MENU = MENUS.register(
                        "dark_farm_menu",
                        () -> IForgeMenuType.create(DarkFarmStationMenu::new));

        public static final RegistryObject<MenuType<TreeFarmStationMenu>> TREE_FARM_MENU = MENUS.register(
                        "tree_farm_menu",
                        () -> IForgeMenuType.create(TreeFarmStationMenu::new));

        public static final RegistryObject<MenuType<ForageFarmStationMenu>> FORAGE_FARM_MENU = MENUS.register(
                        "forage_farm_menu",
                        () -> IForgeMenuType.create(ForageFarmStationMenu::new));
}
