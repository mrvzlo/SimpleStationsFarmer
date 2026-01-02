package com.ave.simplestationsfarmer;

import org.slf4j.Logger;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationscore.registrations.CoreRegistrations;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsFarmer.MODID)
public class SimpleStationsFarmer {
        public static final String MODID = "simplestationsfarmer";
        public static final Logger LOGGER = LogUtils.getLogger();

        public SimpleStationsFarmer(IEventBus modEventBus, ModContainer modContainer) {
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                Registrations.MANAGER.register(modEventBus);
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