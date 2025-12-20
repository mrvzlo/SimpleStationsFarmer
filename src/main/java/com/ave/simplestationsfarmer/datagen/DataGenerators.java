package com.ave.simplestationsfarmer.datagen;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimpleStationsFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput out = generator.getPackOutput();

        if (event.includeServer()) {
            generator.addProvider(event.includeServer(), new ModRecipeProvider(out));
            generator.addProvider(event.includeServer(), ModLootTableProvider.create(out));
        }
    }
}
