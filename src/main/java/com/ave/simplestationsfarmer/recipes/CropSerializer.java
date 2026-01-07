package com.ave.simplestationsfarmer.recipes;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class CropSerializer implements RecipeSerializer<CropRecipe> {
    public static final CropSerializer INSTANCE = new CropSerializer();
    public static final ResourceLocation ID = new ResourceLocation(SimpleStationsFarmer.MODID, "sifter");
    private static final ResourceLocation FALLBACK = ResourceLocation.parse("simplestationscore:part");

    @Override
    public CropRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
        var from = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "from"));
        var to = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "to"));
        var block = new ResourceLocation(GsonHelper.getAsString(json, "block"));
        var station = new ResourceLocation(GsonHelper.getAsString(json, "station"));
        var extra = json.has("extra")
                ? new ResourceLocation(GsonHelper.getAsString(json, "extra"))
                : FALLBACK;

        return new CropRecipe(from, to, block, station, extra.equals(FALLBACK) ? null : extra, pRecipeId);
    }

    @Override
    public CropRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        var from = Ingredient.fromNetwork(buf);
        var to = buf.readItem();
        var block = buf.readResourceLocation();
        var station = buf.readResourceLocation();
        var extra = buf.readResourceLocation();
        return new CropRecipe(from, to, block, station, extra.equals(FALLBACK) ? null : extra, id);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, CropRecipe recipe) {
        recipe.from.toNetwork(buf);
        buf.writeItem(recipe.to);
        buf.writeResourceLocation(recipe.block);
        buf.writeResourceLocation(recipe.station);
        buf.writeResourceLocation(recipe.extra == null ? FALLBACK : recipe.extra);
    }
}
