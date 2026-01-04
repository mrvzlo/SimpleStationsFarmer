package com.ave.simplestationsfarmer.recipes;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, SimpleStationsFarmer.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
            SimpleStationsFarmer.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CropRecipe>> FARM_SERIALIZER = SERIALIZERS
            .register("farm", CropSerializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CropRecipe>> FARM_TYPE = TYPES
            .register("farm", () -> new RecipeType<CropRecipe>() {
            });

    public static Map<Item, Integer> treeToInt = new IdentityHashMap<>();
    public static Map<Item, Integer> cropToInt = new IdentityHashMap<>();
    public static Map<Item, Integer> darkCropToInt = new IdentityHashMap<>();
    public static Map<Item, Integer> foragableToInt = new IdentityHashMap<>();
    public static Map<Integer, CropRecipe> intToCropRecipe = new HashMap<>();;

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
