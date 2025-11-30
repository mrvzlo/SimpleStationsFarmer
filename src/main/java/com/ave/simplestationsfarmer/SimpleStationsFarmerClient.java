package com.ave.simplestationsfarmer;

import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.renderer.StationRenderer;
import com.ave.simplestationsfarmer.screen.ModMenuTypes;
import com.ave.simplestationsfarmer.screen.TreeFarmStationScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import com.ave.simplestationsfarmer.screen.DarkFarmStationScreen;
import com.ave.simplestationsfarmer.screen.FarmStationScreen;
import com.ave.simplestationsfarmer.screen.ForageFarmStationScreen;

@Mod.EventBusSubscriber(modid = SimpleStationsFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SimpleStationsFarmerClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.FARM_MENU.get(), FarmStationScreen::new);
        MenuScreens.register(ModMenuTypes.DARK_FARM_MENU.get(), DarkFarmStationScreen::new);
        MenuScreens.register(ModMenuTypes.TREE_FARM_MENU.get(), TreeFarmStationScreen::new);
        MenuScreens.register(ModMenuTypes.FORAGE_FARM_MENU.get(), ForageFarmStationScreen::new);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PART_ENTITY.get(), StationRenderer::new);
    }
}
