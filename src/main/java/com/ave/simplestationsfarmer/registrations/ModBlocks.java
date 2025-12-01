package com.ave.simplestationsfarmer.registrations;

import java.util.Arrays;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.FarmerBlock;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        SimpleStationsFarmer.MODID);
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        SimpleStationsFarmer.MODID);

        public static final RegistryObject<Block> FARMER_BLOCK = BLOCKS.register("farm",
                        () -> new FarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final RegistryObject<Block> DARK_FARMER_BLOCK = BLOCKS.register("dark_farm",
                        () -> new DarkFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final RegistryObject<Block> TREE_FARMER_BLOCK = BLOCKS.register("tree_farm",
                        () -> new TreeFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final RegistryObject<Block> FORAGE_FARMER_BLOCK = BLOCKS.register("forage_farm",
                        () -> new ForageFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final RegistryObject<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final RegistryObject<BlockItem> FARMER_BLOCK_ITEM = ITEMS.register("farm",
                        () -> new ItemNameBlockItem(FARMER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> DARK_FARMER_BLOCK_ITEM = ITEMS.register("dark_farm",
                        () -> new ItemNameBlockItem(DARK_FARMER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> TREE_FARMER_BLOCK_ITEM = ITEMS.register("tree_farm",
                        () -> new ItemNameBlockItem(TREE_FARMER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> FORAGE_FARMER_BLOCK_ITEM = ITEMS.register("forage_farm",
                        () -> new ItemNameBlockItem(FORAGE_FARMER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<Item> SPRINKLER = ITEMS.register("sprinkler",
                        () -> new Item(new Item.Properties()));

        private static final String[] CROPS = { "wheat", "beet", "carrot", "potato", "melon", "pumpkin",
                        "brown_mushroom", "red_mushroom",
                        "n_wart", "chorus", };

        private static final String[] TREES = { "acacia", "birch", "cherry", "crimson_stem", "dark_oak", "jungle",
                        "mangrove", "oak", "spruce", "warped_stem", "bamboo" };

        private static final String[] FRUITS = { "apple", "cacao", "sweet_berry", "glow_berry", "cactus", "sugar_cane",
                        "flowers" };

        public static final RegistryObject<Block>[] CROP_BLOCKS = Arrays.stream(CROPS)
                        .map(x -> BLOCKS.register(x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(RegistryObject[]::new);

        public static final RegistryObject<Block>[] TREE_EDGE_BLOCKS = Arrays.stream(TREES)
                        .map(x -> BLOCKS.register("edge_" + x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(RegistryObject[]::new);

        public static final RegistryObject<Block>[] TREE_CORNER_BLOCKS = Arrays.stream(TREES)
                        .map(x -> BLOCKS.register("corner_" + x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(RegistryObject[]::new);

        public static final RegistryObject<Block>[] FRUIT_BLOCKS = Arrays.stream(FRUITS)
                        .map(x -> BLOCKS.register(x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(RegistryObject[]::new);

        public static void register(IEventBus bus) {
                BLOCKS.register(bus);
                ITEMS.register(bus);
        }
}
