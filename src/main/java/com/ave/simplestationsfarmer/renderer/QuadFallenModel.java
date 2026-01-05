package com.ave.simplestationsfarmer.renderer;

import net.minecraft.client.resources.model.BakedModel;

public class QuadFallenModel extends BaseQuadModel {
    public QuadFallenModel(BakedModel log) {
        super(log, new Vec4[] {
                new Vec4(2f / 16, 2f / 16, 5f / 16, 3f / 16),
                new Vec4(2f / 16, 2f / 16, 8f / 16, 3f / 16),
                new Vec4(2f / 16, 5f / 16, 13f / 32, 3f / 16),
                new Vec4(5f / 16, 2f / 16, 5f / 16, 3f / 16),
                new Vec4(5f / 16, 2f / 16, 8f / 16, 3f / 16),
                new Vec4(5f / 16, 5f / 16, 13f / 32, 3f / 16),
                new Vec4(8f / 16, 2f / 16, 5f / 16, 3f / 16),
                new Vec4(8f / 16, 2f / 16, 8f / 16, 3f / 16),
                new Vec4(8f / 16, 5f / 16, 13f / 32, 3f / 16),
                new Vec4(11f / 16, 2f / 16, 5f / 16, 3f / 16),
                new Vec4(11f / 16, 2f / 16, 8f / 16, 3f / 16),
                new Vec4(11f / 16, 5f / 16, 13f / 32, 3f / 16),
        });
    }
}