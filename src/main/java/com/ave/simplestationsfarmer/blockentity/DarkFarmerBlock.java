package com.ave.simplestationsfarmer.blockentity;

import javax.annotation.Nullable;

import com.ave.simplestationscore.mainblock.BaseStationBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DarkFarmerBlock extends BaseStationBlock {
    public DarkFarmerBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DarkFarmerBlockEntity(pos, state);
    }
}
