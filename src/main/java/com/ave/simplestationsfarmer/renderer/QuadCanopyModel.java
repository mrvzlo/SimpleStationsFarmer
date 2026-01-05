package com.ave.simplestationsfarmer.renderer;

import net.minecraft.client.resources.model.BakedModel;

public class QuadCanopyModel extends BaseQuadModel {
    public QuadCanopyModel(BakedModel log) {
        super(log, new Vec4[] {
                new Vec4(6f / 16, 11f / 16, 6f / 16, 4f / 16),
                new Vec4(4f / 16, 9f / 16 - 0.001f, 6f / 16 - 0.001f, 4f / 16),
                new Vec4(8f / 16, 9f / 16 - 0.002f, 6f / 16 + 0.001f, 4f / 16),
                new Vec4(6f / 16 + 0.001f, 9f / 16 + 0.001f, 4f / 16, 4f / 16),
                new Vec4(6f / 16 - 0.001f, 9f / 16 + 0.002f, 8f / 16, 4f / 16),
        });
    }
}