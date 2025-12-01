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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimpleStationsFarmer.MODID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleStationsFarmer.MODID);

        public static final DeferredBlock<Block> FARMER_BLOCK = BLOCKS.register("farm",
                        () -> new FarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> DARK_FARMER_BLOCK = BLOCKS.register("dark_farm",
                        () -> new DarkFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> TREE_FARMER_BLOCK = BLOCKS.register("tree_farm",
                        () -> new TreeFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> FORAGE_FARMER_BLOCK = BLOCKS.register("forage_farm",
                        () -> new ForageFarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredItem<BlockItem> FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("farm",
                        FARMER_BLOCK);

        public static final DeferredItem<BlockItem> DARK_FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("dark_farm",
                        DARK_FARMER_BLOCK);

        public static final DeferredItem<BlockItem> TREE_FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("tree_farm",
                        TREE_FARMER_BLOCK);

        public static final DeferredItem<BlockItem> FORAGE_FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
                        "forage_farm",
                        FORAGE_FARMER_BLOCK);

        public static final DeferredItem<Item> SPRINKLER = ITEMS.registerItem("sprinkler", Item::new,
                        new Item.Properties());

        private static final String[] CROPS = { "wheat", "beet", "carrot", "potato", "melon", "pumpkin",
                        "brown_mushroom", "red_mushroom",
                        "n_wart", "chorus", };

        private static final String[] TREES = { "acacia", "birch", "cherry", "crimson_stem", "dark_oak", "jungle",
                        "mangrove", "oak", "spruce", "warped_stem", "bamboo" };

        private static final String[] FRUITS = { "apple", "cacao", "sweet_berry", "glow_berry", "cactus", "sugar_cane",
                        "flowers" };

        public static final DeferredBlock<Block>[] CROP_BLOCKS = Arrays.stream(CROPS)
                        .map(x -> BLOCKS.register(x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(DeferredBlock[]::new);

        public static final DeferredBlock<Block>[] TREE_EDGE_BLOCKS = Arrays.stream(TREES)
                        .map(x -> BLOCKS.register("edge_" + x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(DeferredBlock[]::new);

        public static final DeferredBlock<Block>[] TREE_CORNER_BLOCKS = Arrays.stream(TREES)
                        .map(x -> BLOCKS.register("corner_" + x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(DeferredBlock[]::new);

        public static final DeferredBlock<Block>[] FRUIT_BLOCKS = Arrays.stream(FRUITS)
                        .map(x -> BLOCKS.register(x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(DeferredBlock[]::new);
}
