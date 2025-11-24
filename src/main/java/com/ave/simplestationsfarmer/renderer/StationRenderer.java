package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class StationRenderer implements BlockEntityRenderer<FarmerBlockEntity> {

    public StationRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(FarmerBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        if (be.type == null)
            return;

        ItemStack stack = new ItemStack(Items.WHEAT);
        BakedModel model = itemRenderer.getModel(stack, null, null, 0);

        drawBlock(pose, itemRenderer, stack, buf, 0.5f, -0.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, -0.5f, -0.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, -0.5f, 0.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, 1.5f, 1.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, 0.5f, 1.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, -0.5f, 1.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, 1.5f, 0.5f, model);
        drawBlock(pose, itemRenderer, stack, buf, 1.5f, -0.5f, model);
    }

    private void drawBlock(PoseStack pose, ItemRenderer itemRenderer, ItemStack stack, MultiBufferSource buf, float sx,
            float sz, BakedModel model) {

        pose.pushPose();
        pose.translate(sx, 0.2f, sz);
        pose.scale(0.5f, 0.5f, 0.5f);

        itemRenderer.render(stack, ItemDisplayContext.FIXED, false, pose, buf, 255, OverlayTexture.NO_OVERLAY, model);
        pose.popPose();
    }
}
