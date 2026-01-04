package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DarkFarmItemHandler extends FarmItemHandler {
    public DarkFarmItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseFarmerBlockEntity.FLUID_SLOT)
            return stack.getItem() == Items.LAVA_BUCKET;
        if (slot == BaseFarmerBlockEntity.FERTI_SLOT)
            return stack.getItem() == Items.ROTTEN_FLESH;
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return ModRecipes.darkCropToInt.containsKey(stack.getItem());

        return super.isItemValid(slot, stack);
    }
}