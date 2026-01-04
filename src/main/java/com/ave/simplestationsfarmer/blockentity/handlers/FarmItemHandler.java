package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationscore.handlers.CommonItemHandler;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.recipes.ModRecipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FarmItemHandler extends CommonItemHandler {

    public FarmItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseFarmerBlockEntity.FLUID_SLOT)
            return stack.getItem() == Items.WATER_BUCKET;
        if (slot == BaseFarmerBlockEntity.FERTI_SLOT)
            return stack.getItem() == Items.BONE_MEAL;
        if (slot == BaseFarmerBlockEntity.FUEL_SLOT)
            return stack.getItem() == Items.REDSTONE_BLOCK || stack.getItem() == Items.REDSTONE;
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return ModRecipes.cropToInt.containsKey(stack.getItem());

        return super.isItemValid(slot, stack);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return 1;
        return super.getSlotLimit(slot);
    }
}