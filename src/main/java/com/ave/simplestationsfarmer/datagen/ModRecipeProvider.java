package com.ave.simplestationsfarmer.datagen;

import com.ave.simplestationsfarmer.registrations.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import java.util.function.Consumer;
import net.minecraft.data.recipes.*;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output) {
                super(output);
        }

        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPRINKLER.get())
                                .pattern("RBR")
                                .pattern("HDH")
                                .pattern("RBR")
                                .define('R', Items.REDSTONE)
                                .define('H', Items.HOPPER)
                                .define('B', Items.BUCKET)
                                .define('D', Items.DISPENSER)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FARMER_BLOCK.get())
                                .pattern("DHD")
                                .pattern("LRL")
                                .pattern("DSD")
                                .define('R', ModBlocks.SPRINKLER.get())
                                .define('L', Items.LANTERN)
                                .define('D', Items.DIRT)
                                .define('H', Items.STONE_HOE)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FORAGE_FARMER_BLOCK.get())
                                .pattern("DSD")
                                .pattern("LRL")
                                .pattern("DSD")
                                .define('R', ModBlocks.SPRINKLER.get())
                                .define('L', Items.LANTERN)
                                .define('D', Items.DIRT)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TREE_FARMER_BLOCK.get())
                                .pattern("LAL")
                                .pattern("SBS")
                                .pattern("HAH")
                                .define('S', Items.STONECUTTER)
                                .define('L', Items.LANTERN)
                                .define('B', Items.BUCKET)
                                .define('H', Items.HOPPER)
                                .define('A', Items.STONE_AXE)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DARK_FARMER_BLOCK.get())
                                .pattern("CHC")
                                .pattern("LRL")
                                .pattern("NSN")
                                .define('R', ModBlocks.SPRINKLER.get())
                                .define('L', Items.SOUL_LANTERN)
                                .define('C', Items.COBBLESTONE)
                                .define('N', Items.NETHERRACK)
                                .define('H', Items.STONE_HOE)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(pWriter);
        }
}