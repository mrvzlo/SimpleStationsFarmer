package com.ave.simplestationsfarmer.recipes;

import java.util.List;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.CropType;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
import com.ave.simplestationsfarmer.screen.StationScreen;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
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

        for (var c : CropType.values()) {
            if (c.equals(CropType.Unknown))
                continue;
            recipes.add(new SimpleRecipe(new ItemStack(c.seed), new ItemStack(c.product, c.output)));
        }

        SimpleStationsFarmer.LOGGER.info("Registered recipes: " + recipes.size());
        registration.addRecipes(MinerRecipeCategory.REGULAR, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.FARMER_BLOCK.get()), MinerRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(StationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, MinerRecipeCategory.REGULAR);
    }
}
