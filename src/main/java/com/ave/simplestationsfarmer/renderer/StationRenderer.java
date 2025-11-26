package com.ave.simplestationsfarmer.renderer;

import com.ave.simplestationsfarmer.blockentity.CropType;
import com.ave.simplestationsfarmer.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StationRenderer implements BlockEntityRenderer<PartBlockEntity> {
    private final BakedModel[] models = new BakedModel[CropType.values().length];

    public StationRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        models[CropType.POTATO.ordinal()] = shaper.getBlockModel(ModBlocks.POTATO_BLOCK.get().defaultBlockState());
        models[CropType.CARROT.ordinal()] = shaper.getBlockModel(ModBlocks.CARROT_BLOCK.get().defaultBlockState());
        models[CropType.WHEAT.ordinal()] = shaper.getBlockModel(ModBlocks.WHEAT_BLOCK.get().defaultBlockState());
        models[CropType.BEETROOT.ordinal()] = shaper.getBlockModel(ModBlocks.BEET_BLOCK.get().defaultBlockState());
        models[CropType.SUGAR.ordinal()] = shaper.getBlockModel(ModBlocks.SUGAR_BLOCK.get().defaultBlockState());
        models[CropType.BERRY.ordinal()] = shaper.getBlockModel(ModBlocks.BERRY_BLOCK.get().defaultBlockState());
        models[CropType.PUMPKIN.ordinal()] = shaper.getBlockModel(ModBlocks.PUMPKIN_BLOCK.get().defaultBlockState());
        models[CropType.CACTUS.ordinal()] = shaper.getBlockModel(ModBlocks.CACTUS_BLOCK.get().defaultBlockState());
        models[CropType.MELON.ordinal()] = shaper.getBlockModel(ModBlocks.MELON_BLOCK.get().defaultBlockState());
        models[CropType.GLOWBERRY.ordinal()] = shaper
                .getBlockModel(ModBlocks.GLOWBERRY_BLOCK.get().defaultBlockState());
    }

    @Override
    public void render(PartBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        var type = be.getCropType();
        if (type == null || type.equals(CropType.Unknown))
            return;

        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();
        var model = models[type.ordinal()];
        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

}
