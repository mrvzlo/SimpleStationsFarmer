package com.ave.simplestationsfarmer.datagen;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimpleStationsFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (!event.includeServer())
            return;

        var generator = event.getGenerator();
        var out = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        var blockTags = new ModBlockTagProvider(out, lookup, helper);
        generator.addProvider(true, blockTags);
        generator.addProvider(event.includeServer(), new ModRecipeProvider(out));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(out));

    }
}
