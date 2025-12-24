package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.ModContainer;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CommonItemHandler extends ItemStackHandler {
    private final CropGroup group;

    public CommonItemHandler(int size, CropGroup group) {
        super(size);
        this.group = group;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == ModContainer.OUTPUT_SLOT)
            return false;

        if (slot == BaseFarmerBlockEntity.FLUID_SLOT)
            return stack.getItem() == (group == CropGroup.Dark ? Items.LAVA_BUCKET : Items.WATER_BUCKET);

        if (slot == BaseFarmerBlockEntity.FERTI_SLOT)
            return stack.getItem() == this.group.fertilizer;
        if (slot == BaseFarmerBlockEntity.REDSTONE_SLOT)
            return stack.getItem() == Items.REDSTONE_BLOCK || stack.getItem() == Items.REDSTONE;
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return CropType.findBySeed(stack.getItem()).group == group;

        return super.isItemValid(slot, stack);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == BaseFarmerBlockEntity.TYPE_SLOT)
            return 1;
        return super.getSlotLimit(slot);
    }

    public NonNullList<ItemStack> getAsList() {
        return stacks;
    }
}