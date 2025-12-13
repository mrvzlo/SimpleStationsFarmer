package com.ave.simplestationsfarmer.datagen;

import java.util.List;
import java.util.Set;

import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.FARMER_BLOCK.get());
        dropSelf(ModBlocks.DARK_FARMER_BLOCK.get());
        dropSelf(ModBlocks.FORAGE_FARMER_BLOCK.get());
        dropSelf(ModBlocks.TREE_FARMER_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(ModBlocks.FARMER_BLOCK.get(), ModBlocks.DARK_FARMER_BLOCK.get(),
                ModBlocks.TREE_FARMER_BLOCK.get(), ModBlocks.FORAGE_FARMER_BLOCK.get());
    }
}
