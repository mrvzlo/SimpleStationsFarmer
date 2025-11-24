package com.ave.simplestationsfarmer;

import org.slf4j.Logger;

import com.ave.simplestationsfarmer.blockentity.ModBlockEntities;
import com.ave.simplestationsfarmer.blockentity.FarmerBlock;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlock;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.screen.ModMenuTypes;
import com.ave.simplestationsfarmer.sound.ModSounds;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsFarmer.MODID)
public class SimpleStationsFarmer {
        public static final String MODID = "simplestationsfarmer";
        public static final Logger LOGGER = LogUtils.getLogger();
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);

        public static final DeferredBlock<Block> FARMER_BLOCK = BLOCKS.register("farm",
                        () -> new FarmerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredItem<BlockItem> FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("farm",
                        FARMER_BLOCK);

        public static final DeferredItem<Item> SPRINKLER = ITEMS.registerItem("sprinkler", Item::new,
                        new Item.Properties());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.simplestationsfarmer")) // The language
                                                                                                         // key for
                                        // the title of your
                                        // CreativeModeTab
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> FARMER_BLOCK_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(FARMER_BLOCK_ITEM.get());
                                                output.accept(SPRINKLER.get());
                                        }).build());

        public SimpleStationsFarmer(IEventBus modEventBus, ModContainer modContainer) {
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                BLOCKS.register(modEventBus);
                ITEMS.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
                ModMenuTypes.register(modEventBus);
                ModSounds.SOUND_EVENTS.register(modEventBus);

                modEventBus.addListener(this::addCreative);
                modEventBus.addListener(this::registerCapabilities);
        }

        // Add the example block item to the building blocks tab
        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
                        event.accept(FARMER_BLOCK_ITEM);
        }

        private void registerCapabilities(RegisterCapabilitiesEvent event) {
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((FarmerBlockEntity) be).fuel,
                                FARMER_BLOCK.get());
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((PartBlockEntity) be)
                                                .getEnergyStorage((PartBlockEntity) be),
                                PART.get());
        }
}