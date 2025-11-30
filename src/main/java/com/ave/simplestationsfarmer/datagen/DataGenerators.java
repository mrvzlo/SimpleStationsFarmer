package com.ave.simplestationsfarmer.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = SimpleStationsFarmer.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput out = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        if (event.includeServer()) {
            generator.addProvider(event.includeServer(), new ModRecipeProvider(out, lookup));
        }
    }
}
