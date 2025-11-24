package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.FarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.ModContainer;
import com.ave.simplestationsfarmer.datagen.ModTags;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.ItemStackHandler;

public class SidedItemHandler extends ItemStackHandler {
    public SidedItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == ModContainer.OUTPUT_SLOT)
            return false;
        if (slot == FarmerBlockEntity.WATER_SLOT)
            return stack.getItem() == Items.WATER_BUCKET;
        if (slot == FarmerBlockEntity.FERTI_SLOT)
            return stack.getItem() == Items.BONE_MEAL;
        if (slot == FarmerBlockEntity.REDSTONE_SLOT)
            return stack.getItem() == Items.REDSTONE_BLOCK || stack.getItem() == Items.REDSTONE;

        if (slot == FarmerBlockEntity.TYPE_SLOT)
            return stack.is(ModTags.Items.MINEABLE_TAG);

        return super.isItemValid(slot, stack);
    }

    @Override
    protected int getStackLimit(int slot, ItemStack stack) {
        if (slot == FarmerBlockEntity.TYPE_SLOT)
            return 1;
        return super.getStackLimit(slot, stack);
    }

    public NonNullList<ItemStack> getAsList() {
        return stacks;
    }
}