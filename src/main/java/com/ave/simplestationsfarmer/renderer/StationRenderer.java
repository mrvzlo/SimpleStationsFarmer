package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraftforge.client.model.data.ModelData;

public class StationRenderer implements BlockEntityRenderer<PartBlockEntity> {
    private final int treeOffset = CropType.ACACIA.ordinal();
    private final int fruitOffset = CropType.APPLE.ordinal();
    private final int cropOffset = 1;
    private final BakedModel[] cropModels = new BakedModel[treeOffset - 1];
    private final BakedModel[] treeCornerModels = new BakedModel[CropType.values().length - treeOffset];
    private final BakedModel[] treeEdgeModels = new BakedModel[CropType.values().length - treeOffset];
    private final BakedModel[] fruitModels = new BakedModel[fruitOffset - 1];

    public StationRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        for (var i = 0; i < treeOffset - cropOffset; i++)
            cropModels[i] = shaper.getBlockModel(ModBlocks.CROP_BLOCKS[i].get().defaultBlockState());
        for (var i = 0; i < fruitOffset - treeOffset; i++) {
            treeCornerModels[i] = shaper.getBlockModel(ModBlocks.TREE_CORNER_BLOCKS[i].get().defaultBlockState());
            treeEdgeModels[i] = shaper.getBlockModel(ModBlocks.TREE_EDGE_BLOCKS[i].get().defaultBlockState());
        }
        for (var i = 0; i < CropType.values().length - fruitOffset; i++)
            fruitModels[i] = shaper.getBlockModel(ModBlocks.FRUIT_BLOCKS[i].get().defaultBlockState());
    }

    @Override
    public void render(PartBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        var type = be.getCropType();
        if (type == null || type == CropType.Unknown)
            return;

        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();

        BakedModel model;
        if (type.group == CropGroup.Tree)
            model = getTreeModel(pose, type, be);
        else if (type.group == CropGroup.Forage)
            model = fruitModels[type.ordinal() - fruitOffset];
        else
            model = cropModels[type.ordinal() - cropOffset];

        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

    private BakedModel getTreeModel(PoseStack pose, CropType type, PartBlockEntity be) {
        var model = be.isEdge() ? treeEdgeModels[type.ordinal() - treeOffset]
                : treeCornerModels[type.ordinal() - treeOffset];

        if (be.isEdge() && be.sameZ()) {
            pose.translate(0.5, 0.5, 0.5);
            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.translate(-0.5, -0.5, -0.5);
        }
        return model;
    }
}
