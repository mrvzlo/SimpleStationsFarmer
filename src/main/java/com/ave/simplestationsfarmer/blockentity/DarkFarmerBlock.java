package com.ave.simplestationsfarmer.blockentity;

import javax.annotation.Nullable;

import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DarkFarmerBlock extends BaseFarmerBlock {
    public DarkFarmerBlock(Properties props) {
        super(props, ModBlocks.DARK_FARMER_BLOCK);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DarkFarmerBlockEntity(pos, state);
    }
}
