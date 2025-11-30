package com.ave.simplestationsfarmer;

import org.slf4j.Logger;

import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
import com.ave.simplestationsfarmer.registrations.ModSounds;
import com.ave.simplestationsfarmer.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsFarmer.MODID)
public class SimpleStationsFarmer {
        public static final String MODID = "simplestationsfarmer";
        public static final Logger LOGGER = LogUtils.getLogger();
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);

        public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.simplestations"))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> ModBlocks.FARMER_BLOCK_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModBlocks.FARMER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.TREE_FARMER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.DARK_FARMER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.FORAGE_FARMER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.SPRINKLER.get());
                                        }).build());

        public SimpleStationsFarmer(FMLJavaModLoadingContext context) {
                IEventBus modEventBus = context.getModEventBus();
                context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                ModBlocks.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
                ModMenuTypes.register(modEventBus);
                ModSounds.SOUND_EVENTS.register(modEventBus);

                modEventBus.addListener(this::addCreative);
        }

        // Add the example block item to the building blocks tab
        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
                        event.accept(ModBlocks.FARMER_BLOCK_ITEM);
        }
}