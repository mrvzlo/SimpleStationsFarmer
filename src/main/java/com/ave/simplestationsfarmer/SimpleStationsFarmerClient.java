package com.ave.simplestationsfarmer;

import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.renderer.StationRenderer;
import com.ave.simplestationsfarmer.screen.ModMenuTypes;
import com.ave.simplestationsfarmer.screen.TreeFarmStationScreen;
import com.ave.simplestationsfarmer.screen.DarkFarmStationScreen;
import com.ave.simplestationsfarmer.screen.FarmStationScreen;
import com.ave.simplestationsfarmer.screen.ForageFarmStationScreen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = SimpleStationsFarmer.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods
// in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = SimpleStationsFarmer.MODID, value = Dist.CLIENT)
public class SimpleStationsFarmerClient {
    public SimpleStationsFarmerClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your
        // mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json
        // file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.FARM_MENU.get(), FarmStationScreen::new);
        event.register(ModMenuTypes.DARK_FARM_MENU.get(), DarkFarmStationScreen::new);
        event.register(ModMenuTypes.TREE_FARM_MENU.get(), TreeFarmStationScreen::new);
        event.register(ModMenuTypes.FORAGE_FARM_MENU.get(), ForageFarmStationScreen::new);
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        FarmerBlockEntity.registerCaps(event);
        DarkFarmerBlockEntity.registerCaps(event);
        TreeFarmerBlockEntity.registerCaps(event);
        PartBlockEntity.registerCaps(event);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PART_ENTITY.get(), StationRenderer::new);
    }
}
