package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationscore.handlers.CommonItemHandler;
import com.ave.simplestationsfarmer.blockentity.BaseFarmerBlockEntity;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FarmItemHandler extends CommonItemHandler {
    private final CropGroup group;

    public FarmItemHandler(int size, CropGroup group) {
        super(size);
        this.group = group;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseFarmerBlockEntity.FLUID_SLOT)
            return stack.getItem() == (group == CropGroup.Dark ? Items.LAVA_BUCKET : Items.WATER_BUCKET);
        if (slot == BaseFarmerBlockEntity.FERTI_SLOT)
            return stack.getItem() == this.group.fertilizer;
        if (slot == BaseFarmerBlockEntity.FUEL_SLOT)
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
}