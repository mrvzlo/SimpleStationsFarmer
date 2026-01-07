package com.ave.simplestationsfarmer.recipes;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CropRecipe implements Recipe<SimpleContainer> {
    public final Ingredient from;
    public final ItemStack to;
    public final ResourceLocation block;
    public final ResourceLocation station;
    public final ResourceLocation extra;
    public final ResourceLocation id;

    public CropRecipe(Ingredient from, ItemStack to, ResourceLocation block, ResourceLocation station,
            ResourceLocation extra, ResourceLocation id) {
        this.from = from;
        this.to = to;
        this.block = block;
        this.station = station;
        this.extra = extra;
        this.id = id;
    }

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

        SimpleStationsFarmer.LOGGER.info(" ---- " + hash);
        return list;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level level) {
        if (level.isClientSide())
            return false;
        return from.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public RecipeSerializer<?> getSerializer() {
        return CropSerializer.INSTANCE;
    }

    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public boolean canCraftInDimensions(int arg0, int arg1) {
        return true;
    }

    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
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

    public static class Type implements RecipeType<CropRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "farm";
    }
}
