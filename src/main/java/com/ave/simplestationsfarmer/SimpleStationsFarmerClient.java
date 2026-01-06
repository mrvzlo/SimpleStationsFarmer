package com.ave.simplestationsfarmer;

import com.ave.simplestationscore.registrations.CoreRegistrations;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.renderer.CropRenderer;
import com.ave.simplestationsfarmer.screen.TreeFarmStationScreen;
import com.ave.simplestationsfarmer.screen.DarkFarmStationScreen;
import com.ave.simplestationsfarmer.screen.FarmStationScreen;
import com.ave.simplestationsfarmer.screen.ForageFarmStationScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SimpleStationsFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SimpleStationsFarmerClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(Registrations.FARM_MENU.get(), FarmStationScreen::new);
        MenuScreens.register(Registrations.DARK_FARM_MENU.get(), DarkFarmStationScreen::new);
        MenuScreens.register(Registrations.TREE_FARM_MENU.get(), TreeFarmStationScreen::new);
        MenuScreens.register(Registrations.FORAGE_FARM_MENU.get(), ForageFarmStationScreen::new);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CoreRegistrations.PART.getEntity(), CropRenderer::new);
    }
}
