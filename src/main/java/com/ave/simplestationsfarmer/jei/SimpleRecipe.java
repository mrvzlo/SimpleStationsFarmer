package com.ave.simplestationsfarmer.jei;

import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public final ItemStack filter;
    public final int water = 1000;
    public final int fertilizer = 1;
    public final ItemStack outputType;

    public SimpleRecipe(ItemStack stack, ItemStack out) {
        this.filter = stack;
        outputType = out;
    }
}
