package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class OutputItemHandler implements IItemHandler {
    private final SidedItemHandler parent;

    public OutputItemHandler(SidedItemHandler parent) {
        this.parent = parent;
    }

    @Override
    public int getSlots() {
        return parent.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return parent.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot != BaseFarmerBlockEntity.OUTPUT_SLOT)
            return ItemStack.EMPTY;
        return parent.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return parent.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return parent.isItemValid(slot, stack);
    }
}
