package com.ave.simplestationsfarmer.datagen;

import java.util.List;
import java.util.Set;

import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class ModBlockLootTable extends BlockLootSubProvider {
    protected ModBlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(Registrations.FARMER.getBlock());
        dropSelf(Registrations.DARK_FARMER.getBlock());
        dropSelf(Registrations.FORAGE_FARMER.getBlock());
        dropSelf(Registrations.TREE_FARMER.getBlock());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(Registrations.FARMER.getBlock(), Registrations.DARK_FARMER.getBlock(),
                Registrations.FORAGE_FARMER.getBlock(), Registrations.TREE_FARMER.getBlock());
    }
}
