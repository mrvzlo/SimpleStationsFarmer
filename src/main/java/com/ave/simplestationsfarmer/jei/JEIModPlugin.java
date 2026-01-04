package com.ave.simplestationsfarmer.jei;

import java.util.List;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.recipes.ModRecipes;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.DarkFarmStationScreen;
import com.ave.simplestationsfarmer.screen.FarmStationScreen;
import com.ave.simplestationsfarmer.screen.ForageFarmStationScreen;
import com.ave.simplestationsfarmer.screen.TreeFarmStationScreen;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SimpleStationsFarmer.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DarkFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new TreeFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ForageFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FarmRecipeCategory.REGULAR, this.getCropRecipes(Registrations.FARMER.getBlock()));
        registration.addRecipes(DarkFarmRecipeCategory.REGULAR,
                this.getCropRecipes(Registrations.DARK_FARMER.getBlock()));
        registration.addRecipes(TreeFarmRecipeCategory.REGULAR,
                this.getCropRecipes(Registrations.TREE_FARMER.getBlock()));
        registration.addRecipes(ForageFarmRecipeCategory.REGULAR,
                this.getCropRecipes(Registrations.FORAGE_FARMER.getBlock()));
    }

    private List<SimpleRecipe> getCropRecipes(Block station) {
        List<SimpleRecipe> list = Lists.newArrayList();
        var level = Minecraft.getInstance().level;
        var all = level.getRecipeManager().getAllRecipesFor(ModRecipes.FARM_TYPE.get());
        for (var holder : all) {
            var recipe = holder.value();
            if (!recipe.getStation().equals(station))
                continue;
            var input = new ItemStack(recipe.from().getItems()[0].getItem());
            list.add(new SimpleRecipe(input, recipe.to()));
        }
        return list;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(Registrations.FARMER.getBlock()), FarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.DARK_FARMER.getBlock()), DarkFarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.TREE_FARMER.getBlock()), TreeFarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.FORAGE_FARMER.getBlock()),
                ForageFarmRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FarmStationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, FarmRecipeCategory.REGULAR);
        registration.addRecipeClickArea(DarkFarmStationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, DarkFarmRecipeCategory.REGULAR);
        registration.addRecipeClickArea(TreeFarmStationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, TreeFarmRecipeCategory.REGULAR);
        registration.addRecipeClickArea(ForageFarmStationScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, ForageFarmRecipeCategory.REGULAR);
    }
}
