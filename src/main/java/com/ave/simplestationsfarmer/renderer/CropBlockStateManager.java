package com.ave.simplestationsfarmer.renderer;

import java.util.HashMap;
import java.util.Map;

import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class CropBlockStateManager {
    private static Map<Integer, BakedModel> cropModels = new HashMap<>();

    public static BakedModel get(int hash) {
        if (cropModels.containsKey(hash))
            return cropModels.get(hash);
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var blockState = shaper.getBlockModel(setAge(ModRecipes.intToCropRecipe.get(hash).getBlockState()));
        cropModels.put(hash, blockState);
        return cropModels.get(hash);
    }

    public static BlockState setAge(BlockState state) {
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
        if (state.hasProperty(BlockStateProperties.AGE_1))
            return state.setValue(BlockStateProperties.AGE_2, 1);
        if (state.hasProperty(BlockStateProperties.BERRIES))
            return state.setValue(BlockStateProperties.BERRIES, true);
        if (state.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF))
            return state.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER);
        return state;
    }
}
