package com.ave.simplestationsfarmer.blockentity;

import javax.annotation.Nullable;

import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreeFarmerBlock extends BaseFarmerBlock {
    public TreeFarmerBlock(Properties props) {
        super(props, ModBlocks.TREE_FARMER_BLOCK_ITEM);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TreeFarmerBlockEntity(pos, state);
    }
}
