package com.ave.simplestationsfarmer.registrations;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlock;
import com.ave.simplestationsfarmer.blockentity.FarmerBlock;
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

        public static final DeferredBlock<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredItem<BlockItem> FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("farm",
                        FARMER_BLOCK);

        public static final DeferredItem<BlockItem> DARK_FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("dark_farm",
                        DARK_FARMER_BLOCK);

        public static final DeferredItem<Item> SPRINKLER = ITEMS.registerItem("sprinkler", Item::new,
                        new Item.Properties());

        public static final DeferredBlock<Block> CARROT_BLOCK = BLOCKS.register("carrot",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> POTATO_BLOCK = BLOCKS.register("potato",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> WHEAT_BLOCK = BLOCKS.register("wheat",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> BEET_BLOCK = BLOCKS.register("beet",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> SUGAR_BLOCK = BLOCKS.register("sugar_cane",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> BERRY_BLOCK = BLOCKS.register("sweet_berry",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> CACTUS_BLOCK = BLOCKS.register("cactus",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> PUMPKIN_BLOCK = BLOCKS.register("pumpkin",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> MELON_BLOCK = BLOCKS.register("melon",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> GLOWBERRY_BLOCK = BLOCKS.register("glow_berry",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> RED_MUSHROOM_BLOCK = BLOCKS.register("red_mushroom",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> BROWN_MUSHROOM_BLOCK = BLOCKS.register("brown_mushroom",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> NETHER_WART_BLOCK = BLOCKS.register("n_wart",
                        () -> new Block(BlockBehaviour.Properties.of()));
        public static final DeferredBlock<Block> CHORUS_BLOCK = BLOCKS.register("chorus",
                        () -> new Block(BlockBehaviour.Properties.of()));
}
