package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.world.item.ItemStack;

public class ForagableItemHandler extends FarmItemHandler {
    public ForagableItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return ModRecipes.foragableToInt.containsKey(stack.getItem());

        return super.isItemValid(slot, stack);
    }
}