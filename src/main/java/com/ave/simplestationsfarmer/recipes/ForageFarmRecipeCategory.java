package com.ave.simplestationsfarmer.recipes;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;
import com.ave.simplestationsfarmer.blockentity.ForageFarmerBlockEntity;
import com.ave.simplestationsfarmer.registrations.ModBlocks;
import com.ave.simplestationsfarmer.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ForageFarmRecipeCategory implements IRecipeCategory<SimpleRecipe> {
        private static final String Path = "forage_farm";

        public final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SimpleStationsFarmer.MODID, Path);
        private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
                        SimpleStationsFarmer.MODID,
                        "textures/gui/farm_jei.png");

        public IGuiHelper guiHelper;
        public static RecipeType<SimpleRecipe> REGULAR = RecipeType.create(SimpleStationsFarmer.MODID, Path,
                        SimpleRecipe.class);

        private final IDrawableStatic bg;

        public ForageFarmRecipeCategory(IGuiHelper guiHelper) {
                this.guiHelper = guiHelper;
                bg = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 80);
        }

        @Override
        public RecipeType<SimpleRecipe> getRecipeType() {
                return REGULAR;
        }

        @Override
        public Component getTitle() {
                return Component.translatable("screen.simplestationsfarmer.forage_farm_recipes");
        }

        @Override
        public @Nullable IDrawable getIcon() {
                return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                                new ItemStack(ModBlocks.FORAGE_FARMER_BLOCK_ITEM.get()));
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, SimpleRecipe recipe, IFocusGroup focuses) {
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.RED_SLOT.left, UIBlocks.RED_SLOT.top)
                                .addIngredients(Ingredient.of(Items.REDSTONE));
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FERTI_SLOT.left, UIBlocks.FERTI_SLOT.top)
                                .addIngredients(Ingredient.of(Items.BONE_MEAL));
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.WATER_SLOT.left, UIBlocks.WATER_SLOT.top)
                                .addIngredients(Ingredient.of(Items.WATER_BUCKET));
                if (recipe.filter != null) {
                        builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                        .addItemStack(recipe.outputType);
                        builder.addSlot(RecipeIngredientRole.CATALYST, UIBlocks.FILTER_SLOT.left,
                                        UIBlocks.FILTER_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.filter.getItem()));
                } else if (recipe.tag != null) {
                        builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.tag));
                        builder.addSlot(RecipeIngredientRole.CATALYST, UIBlocks.FILTER_SLOT.left,
                                        UIBlocks.FILTER_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.tag));
                }
        }

        @Override
        public IDrawable getBackground() {
                return bg;
        }

        @Override
        public List<Component> getTooltipStrings(SimpleRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
                        double mouseY) {
                List<Component> list = Lists.newArrayList();
                if (UIBlocks.WATER_BAR.isHovered(mouseX, mouseY))
                        list.add(Component.literal(ForageFarmerBlockEntity.WaterUsage + " mB"));

                return list;
        }
}
