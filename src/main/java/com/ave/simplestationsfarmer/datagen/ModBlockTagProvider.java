package com.ave.simplestationsfarmer.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SimpleStationsFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registrations.FARMER.getBlock())
                .add(Registrations.DARK_FARMER.getBlock())
                .add(Registrations.TREE_FARMER.getBlock())
                .add(Registrations.FORAGE_FARMER.getBlock());
    }
}