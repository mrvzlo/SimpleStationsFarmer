package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.client.model.data.ModelData;

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

        var dispatcher = Minecraft.getInstance().getBlockRenderer();

        if (parent instanceof TreeFarmerBlockEntity) {
            var solid = buf.getBuffer(RenderType.solid());
            if (be.isEdge()) {
                var fallenTree = CropBlockStateManager.getFallenTree(typeCode);
                pose.pushPose();
                if (be.sameZ()) {
                    pose.translate(0.5, 0.5, 0.5);
                    pose.mulPose(Axis.YP.rotationDegrees(90));
                    pose.translate(-0.5, -0.5, -0.5);
                }
                dispatcher.getModelRenderer().renderModel(pose.last(), solid, null,
                        fallenTree, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
                pose.popPose();
            } else {
                var model = CropBlockStateManager.getTree(typeCode);
                pose.pushPose();
                dispatcher.getModelRenderer().renderModel(pose.last(), solid, null,
                        model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
                pose.popPose();
                var leaves = CropBlockStateManager.getLeaves(typeCode);
                pose.pushPose();
                dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutoutMipped()), null,
                        leaves, 0.5f, 0.9f, 0.2f, light, overlay, ModelData.EMPTY, null);
                pose.popPose();
            }

        } else {
            var model = CropBlockStateManager.get(typeCode);
            var cutout = buf.getBuffer(RenderType.cutout());
            pose.pushPose();
            dispatcher.getModelRenderer().renderModel(pose.last(), cutout, null,
                    model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
            pose.popPose();
        }
    }
}
