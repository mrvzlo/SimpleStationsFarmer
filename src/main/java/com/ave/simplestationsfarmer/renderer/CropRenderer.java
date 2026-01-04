package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.neoforged.neoforge.client.model.data.ModelData;

public class CropRenderer implements BlockEntityRenderer<PartBlockEntity> {
    public CropRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PartBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light, int overlay) {
        var parent = be.getController();
        if (!(parent instanceof BaseFarmerBlockEntity))
            return;
        var typeCode = be.getStationType();
        if (typeCode == -1)
            return;

        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();

        var model = CropBlockStateManager.get(typeCode);
        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);

        pose.popPose();
    }

    // private BakedModel getTreeModel(PoseStack pose, int type, PartBlockEntity be)
    // {
    // var model = be.isEdge() ? treeEdgeModels[type] : treeCornerModels[type];

    // if (be.isEdge() && be.sameZ()) {
    // pose.translate(0.5, 0.5, 0.5);
    // pose.mulPose(Axis.YP.rotationDegrees(90));
    // pose.translate(-0.5, -0.5, -0.5);
    // }
    // return model;
    // }
}
