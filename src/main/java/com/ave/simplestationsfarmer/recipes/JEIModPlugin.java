package com.ave.simplestationsfarmer.recipes;

import java.util.List;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.datagen.ModTags;
import com.ave.simplestationsfarmer.screen.StationScreen;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SimpleStationsFarmer.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MinerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<SimpleRecipe> recipes = Lists.newArrayList();
        for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(ModTags.Items.MINEABLE_TAG)) {
            ItemStack stack = new ItemStack(item);
            recipes.add(new SimpleRecipe(stack, FarmerBlockEntity.getOutputSize(item.value())));
        }
        registration.addRecipes(MinerRecipeCategory.REGULAR, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(SimpleStationsFarmer.FARMER_BLOCK.get()), MinerRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(StationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, MinerRecipeCategory.REGULAR);
    }
}
