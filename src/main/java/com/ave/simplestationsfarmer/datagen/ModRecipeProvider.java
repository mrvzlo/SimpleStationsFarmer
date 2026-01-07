package com.ave.simplestationsfarmer.datagen;

import java.util.function.Consumer;

import com.ave.simplestationsfarmer.registrations.Registrations;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output) {
                super(output);
        }

        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.SPRINKLER.get())
                                .pattern("RBR")
                                .pattern("HDH")
                                .pattern("RBR")
                                .define('R', Items.REDSTONE)
                                .define('H', Items.HOPPER)
                                .define('B', Items.BUCKET)
                                .define('D', Items.DISPENSER)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.FARMER.getBlock())
                                .pattern("DHD")
                                .pattern("LRL")
                                .pattern("DSD")
                                .define('R', Registrations.SPRINKLER.get())
                                .define('L', Items.LANTERN)
                                .define('D', Items.DIRT)
                                .define('H', Items.STONE_HOE)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.FORAGE_FARMER.getBlock())
                                .pattern("DSD")
                                .pattern("LRL")
                                .pattern("DSD")
                                .define('R', Registrations.SPRINKLER.get())
                                .define('L', Items.LANTERN)
                                .define('D', Items.DIRT)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.TREE_FARMER.getBlock())
                                .pattern("LAL")
                                .pattern("SBS")
                                .pattern("HAH")
                                .define('S', Items.STONECUTTER)
                                .define('L', Items.LANTERN)
                                .define('B', Items.BUCKET)
                                .define('H', Items.HOPPER)
                                .define('A', Items.STONE_AXE)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.DARK_FARMER.getBlock())
                                .pattern("CHC")
                                .pattern("LRL")
                                .pattern("NSN")
                                .define('R', Registrations.SPRINKLER.get())
                                .define('L', Items.SOUL_LANTERN)
                                .define('C', Items.COBBLESTONE)
                                .define('N', Items.NETHERRACK)
                                .define('H', Items.STONE_HOE)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);
        }
}