package com.ave.simplestationsfarmer.renderer;

import java.util.HashMap;
import java.util.Map;

import com.ave.simplestationsfarmer.recipes.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class CropBlockStateManager {
    private static Map<Integer, BakedModel> cropModels = new HashMap<>();
    private static Map<Integer, BakedModel> treeModels = new HashMap<>();
    private static Map<Integer, BakedModel> fallenModels = new HashMap<>();
    private static Map<Integer, BakedModel> canopyModels = new HashMap<>();

    public static BakedModel get(int hash) {
        if (cropModels.containsKey(hash))
            return cropModels.get(hash);

        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var baked = shaper.getBlockModel(findByHash(hash));
        var model = new QuadDuplicatingModel(baked);
        cropModels.put(hash, model);
        return cropModels.get(hash);
    }

    public static BakedModel getTree(int hash) {
        if (treeModels.containsKey(hash))
            return treeModels.get(hash);

        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var log = shaper.getBlockModel(findByHash(hash));
        var model = new QuadTreeModel(log);
        treeModels.put(hash, model);
        return treeModels.get(hash);
    }

    public static BakedModel getFallenTree(int hash) {
        if (fallenModels.containsKey(hash))
            return fallenModels.get(hash);

        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var logState = findByHash(hash);
        logState = logState.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
        var log = shaper.getBlockModel(logState);
        var model = new QuadFallenModel(log);
        fallenModels.put(hash, model);
        return fallenModels.get(hash);
    }

    public static BakedModel getLeaves(int hash) {
        if (canopyModels.containsKey(hash))
            return canopyModels.get(hash);

        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        var log = shaper.getBlockModel(findExtraByHash(hash));
        var model = new QuadCanopyModel(log);
        canopyModels.put(hash, model);
        return canopyModels.get(hash);
    }

    private static BlockState findByHash(int hash) {
        var blockState = ModRecipes.intToCropRecipe.get(hash).getBlockState();
        return setProps(blockState);
    }

    private static BlockState findExtraByHash(int hash) {
        return ModRecipes.intToCropRecipe.get(hash).getExtraState();
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
