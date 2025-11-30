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
import net.neoforged.neoforge.client.model.data.ModelData;

public class StationRenderer implements BlockEntityRenderer<PartBlockEntity> {
    private final int treeOffset = CropType.ACACIA.ordinal();
    private final int cropOffset = 1;
    private final BakedModel[] cropModels = new BakedModel[treeOffset - 1];
    private final BakedModel[] treeCornerModels = new BakedModel[CropType.values().length - treeOffset];
    private final BakedModel[] treeEdgeModels = new BakedModel[CropType.values().length - treeOffset];

    public StationRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        for (var i = 0; i < treeOffset - cropOffset; i++)
            cropModels[i] = shaper.getBlockModel(ModBlocks.CROP_BLOCKS[i].get().defaultBlockState());
        for (var i = 0; i < CropType.values().length - treeOffset; i++) {
            treeCornerModels[i] = shaper.getBlockModel(ModBlocks.TREE_CORNER_BLOCKS[i].get().defaultBlockState());
            treeEdgeModels[i] = shaper.getBlockModel(ModBlocks.TREE_EDGE_BLOCKS[i].get().defaultBlockState());
        }
    }

    @Override
    public void render(PartBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        var type = be.getCropType();
        if (type == null || type == CropType.Unknown)
            return;

        if (type.group == CropGroup.Tree)
            drawTree(pose, buf, light, overlay, type, be);
        else
            drawCrops(pose, buf, light, overlay, type);
    }

    private void drawTree(PoseStack pose, MultiBufferSource buf, int light, int overlay, CropType type,
            PartBlockEntity be) {
        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();
        var model = be.isEdge() ? treeEdgeModels[type.ordinal() - treeOffset]
                : treeCornerModels[type.ordinal() - treeOffset];

        if (be.isEdge() && be.sameZ()) {
            pose.translate(0.5, 0.5, 0.5);
            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.translate(-0.5, -0.5, -0.5);
        }

        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

    private void drawCrops(PoseStack pose, MultiBufferSource buf, int light, int overlay, CropType type) {
        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();
        var model = cropModels[type.ordinal() - cropOffset];
        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }
}
