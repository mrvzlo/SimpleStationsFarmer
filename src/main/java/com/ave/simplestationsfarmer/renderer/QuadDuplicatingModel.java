package com.ave.simplestationsfarmer.renderer;

import net.minecraft.client.resources.model.BakedModel;

public class QuadDuplicatingModel extends BaseQuadModel {
    public QuadDuplicatingModel(BakedModel base) {
        super(base, new Vec4[] {
                new Vec4(1f / 16, 0.15f, 1f / 16, 6f / 16),
                new Vec4(9f / 16, 0.15f, 1f / 16, 6f / 16),
                new Vec4(1f / 16, 0.15f, 9f / 16, 6f / 16),
                new Vec4(9f / 16, 0.15f, 9f / 16, 6f / 16)
        });
    }
}