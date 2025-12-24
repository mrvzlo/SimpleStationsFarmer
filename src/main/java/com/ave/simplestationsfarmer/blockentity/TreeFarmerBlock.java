package com.ave.simplestationsfarmer.blockentity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreeFarmerBlock extends BaseFarmerBlock {
    public TreeFarmerBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TreeFarmerBlockEntity(pos, state);
    }
}
