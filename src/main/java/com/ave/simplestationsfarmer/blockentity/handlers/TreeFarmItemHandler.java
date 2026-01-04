package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.world.item.ItemStack;

public class TreeFarmItemHandler extends FarmItemHandler {
    public TreeFarmItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return ModRecipes.treeToInt.containsKey(stack.getItem());

        return super.isItemValid(slot, stack);
    }
}