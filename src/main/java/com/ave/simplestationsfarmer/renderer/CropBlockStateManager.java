package com.ave.simplestationsfarmer.renderer;

import java.util.HashMap;
import java.util.Map;

import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class CropBlockStateManager {
    private static Map<Integer, QuadDuplicatingModel> cropModels = new HashMap<>();

    public static QuadDuplicatingModel get(int hash) {
        if (cropModels.containsKey(hash))
            return cropModels.get(hash);

        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var blockState = findByHash(hash);
        var baked = shaper.getBlockModel(blockState);
        var model = new QuadDuplicatingModel(baked, blockState);
        cropModels.put(hash, model);
        return cropModels.get(hash);
    }

    private static BlockState findByHash(int hash) {
        var blockState = ModRecipes.intToCropRecipe.get(hash).getBlockState();
        return setProps(blockState);
    }

    private static BlockState setProps(BlockState state) {
        if (state.hasProperty(BlockStateProperties.AGE_7))
            return state.setValue(BlockStateProperties.AGE_7, 7);
        if (state.hasProperty(BlockStateProperties.AGE_5))
            return state.setValue(BlockStateProperties.AGE_5, 5);
        if (state.hasProperty(BlockStateProperties.AGE_4))
            return state.setValue(BlockStateProperties.AGE_4, 4);
        if (state.hasProperty(BlockStateProperties.AGE_3))
            return state.setValue(BlockStateProperties.AGE_3, 3);
        if (state.hasProperty(BlockStateProperties.AGE_2))
            return state.setValue(BlockStateProperties.AGE_2, 2);
        if (state.hasProperty(BlockStateProperties.BERRIES))
            return state.setValue(BlockStateProperties.BERRIES, true);
        if (state.hasProperty(BlockStateProperties.FACING))
            return state.setValue(BlockStateProperties.FACING, Direction.NORTH);
        if (state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF))
            return state.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER);
        return state;
    }
}
