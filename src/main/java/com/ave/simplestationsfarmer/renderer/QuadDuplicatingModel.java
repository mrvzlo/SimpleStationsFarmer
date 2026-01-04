package com.ave.simplestationsfarmer.renderer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;

public class QuadDuplicatingModel implements BakedModel {
    private final BakedModel base;
    private final BlockState state;
    private final List<BakedQuad> cachedQuads;

    private static final float p1 = 1 / 16f;
    private static final float p6 = 6 / 16f;
    private static final float p9 = 9 / 16f;

    public QuadDuplicatingModel(BakedModel base, BlockState state) {
        this.base = base;
        this.cachedQuads = bake();
        this.state = state;
    }

    private List<BakedQuad> bake() {
        List<BakedQuad> result = new ArrayList<>();

        List<BakedQuad> baseQuads = base.getQuads(state, null, RandomSource.create(), ModelData.EMPTY, null);
        for (Direction dir : Direction.values())
            baseQuads.addAll(base.getQuads(state, dir, RandomSource.create(), ModelData.EMPTY, null));

        var offsets = new Vec3[] {
                new Vec3(p1, 0.15f, p1),
                new Vec3(p9, 0.15f, p1),
                new Vec3(p1, 0.15f, p9),
                new Vec3(p9, 0.15f, p9)
        };

        for (var offset : offsets)
            for (var quad : baseQuads)
                result.add(transformQuad(quad, offset));

        return result;
    }

    private static BakedQuad transformQuad(BakedQuad quad, Vec3 offset) {
        int[] data = quad.getVertices().clone();

        for (int i = 0; i < 4; i++) {
            int base = i * 8;

            float x = Float.intBitsToFloat(data[base]);
            float y = Float.intBitsToFloat(data[base + 1]);
            float z = Float.intBitsToFloat(data[base + 2]);

            x *= p6;
            y *= p6;
            z *= p6;

            x += offset.x();
            z += offset.z();
            y += offset.y();

            data[base] = Float.floatToRawIntBits(x);
            data[base + 1] = Float.floatToRawIntBits(y);
            data[base + 2] = Float.floatToRawIntBits(z);
        }

        return new BakedQuad(data, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
    }

    @Override
    public boolean useAmbientOcclusion() {
        return base.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return base.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return base.usesBlockLight();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return base.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return base.getOverrides();
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction direction, RandomSource random) {
        return direction == null ? cachedQuads : List.of();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

}