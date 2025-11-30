package com.ave.simplestationsfarmer.recipes;

import java.util.List;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
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
        registration.addRecipeCategories(new FarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DarkFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new TreeFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ForageFarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FarmRecipeCategory.REGULAR, this.getRecipes(CropGroup.Crop));
        registration.addRecipes(DarkFarmRecipeCategory.REGULAR, this.getRecipes(CropGroup.Dark));
        registration.addRecipes(TreeFarmRecipeCategory.REGULAR, this.getRecipes(CropGroup.Tree));
        registration.addRecipes(ForageFarmRecipeCategory.REGULAR, this.getRecipes(CropGroup.Forage));
    }

    private List<SimpleRecipe> getRecipes(CropGroup group) {
        List<SimpleRecipe> list = Lists.newArrayList();
        for (var c : CropType.values()) {
            if (c.equals(CropType.Unknown) || !c.group.equals(group))
                continue;
            if (c.seed != null)
                list.add(new SimpleRecipe(new ItemStack(c.seed), new ItemStack(c.product, c.output)));
            if (c.tag != null)
                list.add(new SimpleRecipe(c.tag));
        }
        return list;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.FARMER_BLOCK.get()), FarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.DARK_FARMER_BLOCK.get()), DarkFarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.TREE_FARMER_BLOCK.get()), TreeFarmRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.FORAGE_FARMER_BLOCK.get()),
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
