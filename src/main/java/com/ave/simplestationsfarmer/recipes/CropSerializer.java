package com.ave.simplestationsfarmer.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CropSerializer implements RecipeSerializer<CropRecipe> {
    private static final ResourceLocation FALLBACK = ResourceLocation.parse("simplestationscore:part");

    public static final MapCodec<CropRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("from").forGetter(CropRecipe::from),
            ItemStack.CODEC.fieldOf("to").forGetter(CropRecipe::to),
            ResourceLocation.CODEC.fieldOf("block").forGetter(CropRecipe::block),
            ResourceLocation.CODEC.fieldOf("station").forGetter(CropRecipe::station),
            ResourceLocation.CODEC.optionalFieldOf("extra", FALLBACK)
                    .forGetter(r -> r.extra() == null ? FALLBACK : r.extra()))
            .apply(inst, (from, to, block, station, extra) -> new CropRecipe(from, to, block, station, extra)));

    public static final StreamCodec<RegistryFriendlyByteBuf, CropRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CropRecipe::from,
            ItemStack.STREAM_CODEC, CropRecipe::to,
            ResourceLocation.STREAM_CODEC, CropRecipe::block,
            ResourceLocation.STREAM_CODEC, CropRecipe::station,
            ResourceLocation.STREAM_CODEC, r -> r.extra() == null ? FALLBACK : r.extra(),
            (from, to, block, station, extra) -> new CropRecipe(from, to, block, station, extra));

    @Override
    public MapCodec<CropRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CropRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
