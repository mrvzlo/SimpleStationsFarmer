package com.ave.simplestationsfarmer.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            BlockTagsProvider blockTags,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags.contentsGetter(), SimpleStationsFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
