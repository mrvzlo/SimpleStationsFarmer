package com.ave.simplestationsfarmer.registrations;

import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationscore.registrations.Station;
import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.FarmerBlock;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.screen.DarkFarmStationMenu;
import com.ave.simplestationsfarmer.screen.FarmStationMenu;
import com.ave.simplestationsfarmer.screen.ForageFarmStationMenu;
import com.ave.simplestationsfarmer.screen.TreeFarmStationMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class Registrations {
        public static final RegistrationManager MANAGER = new RegistrationManager(SimpleStationsFarmer.MODID);

        public static final Station<FarmerBlockEntity, FarmerBlock> FARMER = MANAGER.registerStation(
                        "farm", (p) -> new FarmerBlock(p), FarmerBlockEntity::new);
        public static final Station<DarkFarmerBlockEntity, DarkFarmerBlock> DARK_FARMER = MANAGER.registerStation(
                        "dark_farm", (p) -> new DarkFarmerBlock(p), DarkFarmerBlockEntity::new);
        public static final Station<TreeFarmerBlockEntity, TreeFarmerBlock> TREE_FARMER = MANAGER.registerStation(
                        "tree_farm", (p) -> new TreeFarmerBlock(p), TreeFarmerBlockEntity::new);
        public static final Station<ForageFarmerBlockEntity, ForageFarmerBlock> FORAGE_FARMER = MANAGER.registerStation(
                        "forage_farm", (p) -> new ForageFarmerBlock(p), ForageFarmerBlockEntity::new);

        public static final RegistryObject<Item> SPRINKLER = MANAGER.registerItem("sprinkler");

        private static final String[] FORAGABLE = { "apple", "cacao" };
        public static final RegistryObject<Block>[] FORAGABLE_BLOCKS = MANAGER.registerEmptyBlocks("", FORAGABLE);

        public static final RegistryObject<MenuType<FarmStationMenu>> FARM_MENU = MANAGER
                        .registerMenuType("farm_menu", FarmStationMenu::new);

        public static final RegistryObject<MenuType<DarkFarmStationMenu>> DARK_FARM_MENU = MANAGER
                        .registerMenuType("dark_farm_menu", DarkFarmStationMenu::new);

        public static final RegistryObject<MenuType<TreeFarmStationMenu>> TREE_FARM_MENU = MANAGER
                        .registerMenuType("tree_farm_menu", TreeFarmStationMenu::new);

        public static final RegistryObject<MenuType<ForageFarmStationMenu>> FORAGE_FARM_MENU = MANAGER
                        .registerMenuType("forage_farm_menu", ForageFarmStationMenu::new);
}
