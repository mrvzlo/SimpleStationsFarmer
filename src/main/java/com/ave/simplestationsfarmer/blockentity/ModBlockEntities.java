package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(Registries.BLOCK_ENTITY_TYPE, SimpleStationsFarmer.MODID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FarmerBlockEntity>> MINER_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("miner",
                                        () -> BlockEntityType.Builder
                                                        .of(FarmerBlockEntity::new,
                                                                        SimpleStationsFarmer.FARMER_BLOCK.get())
                                                        .build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PartBlockEntity>> PART_BLOCK_ENTITY = BLOCK_ENTITIES
                        .register("part", () -> BlockEntityType.Builder
                                        .of(PartBlockEntity::new, SimpleStationsFarmer.PART.get()).build(null));
}