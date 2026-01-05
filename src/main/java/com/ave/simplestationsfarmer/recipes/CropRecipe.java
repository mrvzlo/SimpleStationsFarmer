package com.ave.simplestationsfarmer.recipes;

import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record CropRecipe(Ingredient from, ItemStack to, ResourceLocation block, ResourceLocation station,
        ResourceLocation extra) implements Recipe<CropRecipeInput> {

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(from);

        var hash = to.getItem().toString().hashCode();
        var filter = from.getItems()[0].getItem();
        var station = getStation();
        if (station.equals(Registrations.FARMER.getBlock()))
            ModRecipes.cropToInt.put(filter, hash);
        if (station.equals(Registrations.DARK_FARMER.getBlock()))
            ModRecipes.darkCropToInt.put(filter, hash);
        if (station.equals(Registrations.FORAGE_FARMER.getBlock()))
            ModRecipes.foragableToInt.put(filter, hash);
        if (station.equals(Registrations.TREE_FARMER.getBlock()))
            ModRecipes.treeToInt.put(filter, hash);
        ModRecipes.intToCropRecipe.put(hash, this);
        return list;
    }

    public boolean matches(CropRecipeInput input, Level level) {
        return !level.isClientSide() && from.test(input.getItem(0));
    }

    public ItemStack assemble(CropRecipeInput input, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    public RecipeSerializer<? extends Recipe<CropRecipeInput>> getSerializer() {
        return ModRecipes.FARM_SERIALIZER.get();
    }

    public RecipeType<? extends Recipe<CropRecipeInput>> getType() {
        return ModRecipes.FARM_TYPE.get();
    }

    public boolean canCraftInDimensions(int arg0, int arg1) {
        return true;
    }

    public ItemStack getResultItem(Provider arg0) {
        return ItemStack.EMPTY;
    }

    public BlockState getBlockState() {
        return BuiltInRegistries.BLOCK.get(block).defaultBlockState();
    }

    public BlockState getExtraState() {
        return BuiltInRegistries.BLOCK.get(extra).defaultBlockState();
    }

    public Block getStation() {
        return BuiltInRegistries.BLOCK.get(station);
    }
}
