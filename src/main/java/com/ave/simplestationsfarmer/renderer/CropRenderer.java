package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.TreeFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.enums.*;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class CropRenderer implements BlockEntityRenderer<PartBlockEntity> {
    private final BakedModel[] treeCornerModels = new BakedModel[TreeType.values().length];
    private final BakedModel[] treeEdgeModels = new BakedModel[TreeType.values().length];

    public CropRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        for (var i = 0; i < treeEdgeModels.length; i++) {
            treeCornerModels[i] = shaper.getBlockModel(Registrations.TREE_CORNER_BLOCKS[i].get().defaultBlockState());
            treeEdgeModels[i] = shaper.getBlockModel(Registrations.TREE_EDGE_BLOCKS[i].get().defaultBlockState());
        }
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

        BakedModel model;
        if (parent instanceof TreeFarmerBlockEntity)
            model = getTreeModel(pose, typeCode, be);
        else
            model = CropBlockStateManager.get(typeCode);

        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

    private BakedModel getTreeModel(PoseStack pose, int type, PartBlockEntity be) {
        var model = be.isEdge() ? treeEdgeModels[type] : treeCornerModels[type];

        if (be.isEdge() && be.sameZ()) {
            pose.translate(0.5, 0.5, 0.5);
            pose.mulPose(Axis.YP.rotationDegrees(90));
            pose.translate(-0.5, -0.5, -0.5);
        }
        return model;
    }
}
