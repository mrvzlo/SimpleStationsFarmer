package com.ave.simplestationsfarmer;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationscore.registrations.CoreRegistrations;
import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.renderer.CropRenderer;
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
        event.register(Registrations.FARM_MENU.get(), FarmStationScreen::new);
        event.register(Registrations.DARK_FARM_MENU.get(), DarkFarmStationScreen::new);
        event.register(Registrations.TREE_FARM_MENU.get(), TreeFarmStationScreen::new);
        event.register(Registrations.FORAGE_FARM_MENU.get(), ForageFarmStationScreen::new);
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        RegistrationManager.registerCaps(event, Registrations.FARMER.getEntity());
        RegistrationManager.registerCaps(event, Registrations.DARK_FARMER.getEntity());
        RegistrationManager.registerCaps(event, Registrations.TREE_FARMER.getEntity());
        RegistrationManager.registerCaps(event, Registrations.FORAGE_FARMER.getEntity());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CoreRegistrations.PART.getEntity(), CropRenderer::new);
    }
}
