package com.ave.simplestationsfarmer;

import org.slf4j.Logger;

import com.ave.simplestationscore.registrations.CoreRegistrations;
import com.ave.simplestationsfarmer.recipes.ModRecipes;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.mojang.logging.LogUtils;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SimpleStationsFarmer.MODID)
public class SimpleStationsFarmer {
        public static final String MODID = "simplestationsfarmer";
        public static final Logger LOGGER = LogUtils.getLogger();

        public SimpleStationsFarmer(FMLJavaModLoadingContext context) {
                IEventBus modEventBus = context.getModEventBus();
                context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                Registrations.MANAGER.register(modEventBus);
                ModRecipes.register(modEventBus);
                modEventBus.addListener(this::addCreative);
        }

        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (!event.getTab().equals(CoreRegistrations.CREATIVE_TAB.get()))
                        return;
                event.accept(Registrations.FARMER.getItem());
                event.accept(Registrations.TREE_FARMER.getItem());
                event.accept(Registrations.DARK_FARMER.getItem());
                event.accept(Registrations.FORAGE_FARMER.getItem());
                event.accept(Registrations.SPRINKLER.get());
        }
}