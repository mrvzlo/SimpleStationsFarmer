package com.ave.simplestationsfarmer.recipes;

import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public final ItemStack filter;
    public final int catalysis;
    public final int fertilizer;
    public final int outputSize;
    public final ItemStack outputType;

    public SimpleRecipe(ItemStack stack, int out) {
        this.filter = stack;
        catalysis = 1;
        fertilizer = 1;
        outputSize = out;
    }
}
