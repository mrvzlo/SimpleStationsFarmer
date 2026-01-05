package com.ave.simplestationsfarmer.renderer;

import net.minecraft.client.resources.model.BakedModel;

public class QuadTreeModel extends BaseQuadModel {
    public QuadTreeModel(BakedModel log) {
        super(log, new Vec4[] {
                new Vec4(13f / 32, 0f / 16, 13f / 32, 3f / 16),
                new Vec4(13f / 32, 3f / 16, 13f / 32, 3f / 16),
                new Vec4(13f / 32, 6f / 16, 13f / 32, 3f / 16),
                new Vec4(13f / 32, 9f / 16, 13f / 32, 3f / 16),
        });
    }
}