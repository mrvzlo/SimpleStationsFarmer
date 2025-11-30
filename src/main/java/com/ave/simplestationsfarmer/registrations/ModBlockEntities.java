package com.ave.simplestationsfarmer.registrations;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.DarkFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(Registries.BLOCK_ENTITY_TYPE, SimpleStationsFarmer.MODID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FarmerBlockEntity>> FARMER_ENTITY = BLOCK_ENTITIES
                        .register("farmer", () -> BlockEntityType.Builder
                                        .of(FarmerBlockEntity::new, ModBlocks.FARMER_BLOCK.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DarkFarmerBlockEntity>> DARK_FARMER_ENTITY = BLOCK_ENTITIES
                        .register("dark_farmer", () -> BlockEntityType.Builder
                                        .of(DarkFarmerBlockEntity::new, ModBlocks.DARK_FARMER_BLOCK.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TreeFarmerBlockEntity>> TREE_FARMER_ENTITY = BLOCK_ENTITIES
                        .register("tree_farmer", () -> BlockEntityType.Builder
                                        .of(TreeFarmerBlockEntity::new, ModBlocks.TREE_FARMER_BLOCK.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PartBlockEntity>> PART_ENTITY = BLOCK_ENTITIES
                        .register("part", () -> BlockEntityType.Builder
                                        .of(PartBlockEntity::new, ModBlocks.PART.get()).build(null));
}