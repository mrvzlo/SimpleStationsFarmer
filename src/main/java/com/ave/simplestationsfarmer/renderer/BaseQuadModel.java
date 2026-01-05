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
import net.neoforged.neoforge.client.model.data.ModelData;

public abstract class BaseQuadModel implements BakedModel {
    public List<BakedQuad> cachedQuads;
    private final BakedModel base;
    private Vec4[] offsets;

    protected boolean isLeaves = false;

    public BaseQuadModel(BakedModel base, Vec4[] offsets) {
        this.base = base;
        this.offsets = offsets;
        this.cachedQuads = bake();
    }

    protected List<BakedQuad> bake() {
        List<BakedQuad> result = new ArrayList<>();

        var quads = base.getQuads(null, null, RandomSource.create(), ModelData.EMPTY, null);
        for (var dir : Direction.values())
            quads.addAll(base.getQuads(null, dir, RandomSource.create(), ModelData.EMPTY, null));

        for (var offset : offsets)
            for (var quad : quads)
                result.add(transformQuad(quad, offset));

        return result;
    }

    protected BakedQuad transformQuad(BakedQuad quad, Vec4 offset) {
        int[] data = quad.getVertices().clone();

        for (int i = 0; i < 4; i++) {
            int base = i * 8;
            data[base] = adjust(data[base], offset.s, offset.x);
            data[base + 1] = adjust(data[base + 1], offset.s, offset.y);
            data[base + 2] = adjust(data[base + 2], offset.s, offset.z);
        }

        return new BakedQuad(data, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
    }

    private int adjust(int from, float mult, double inc) {
        return Float.floatToRawIntBits(Float.intBitsToFloat(from) * mult + (float) inc);
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