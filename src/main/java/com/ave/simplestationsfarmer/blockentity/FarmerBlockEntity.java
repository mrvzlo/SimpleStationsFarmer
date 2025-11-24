package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.sound.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

public class FarmerBlockEntity extends ModContainer {
    public EnergyStorage fuel;
    public Item type = null;
    public float progress = 0;
    public int fertilizer = 0;
    public int redstone = 0;
    public boolean working = false;

    private int outputSize = 1;

    public FarmerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINER_BLOCK_ENTITY.get(), pos, state, 5);
        fuel = new EnergyStorage(Config.POWER_MAX.get());
    }

    public void tick() {
        if (level.isClientSide)
            return;

        if (progress >= Config.MAX_PROGRESS.get())
            progress -= Config.MAX_PROGRESS.get();

        checkNewType();
        checkResource(WATER_SLOT, Items.COAL_BLOCK, Config.POWER_PER_RED.get(), Config.POWER_MAX.get(),
                ResourceType.FUEL);

        checkResource(REDSTONE_SLOT, Items.REDSTONE_BLOCK, 1, Config.WATER_MAX.get(), ResourceType.POWER);
        checkResource(FERTI_SLOT, Items.LAPIS_BLOCK, 1, Config.FERT_MAX.get(), ResourceType.FERT);

        ItemStack slot = inventory.getStackInSlot(OUTPUT_SLOT);
        working = getWorking(slot);

        if (type == null || !working)
            return;

        progress++;

        if (fertilizer > 0) {
            fertilizer--;
            progress += Config.FERT_MULT.get();
        }
        if (redstone > 0) {
            redstone--;
            progress += Config.POWER_MULT.get();
        }
        fuel.extractEnergy(Config.WATER_PER_CYCLE.get(), false);
        playSound();

        if (progress < Config.MAX_PROGRESS.get())
            return;

        ItemStack toAdd = new ItemStack(type);
        toAdd.setCount(slot.getCount() + outputSize);
        inventory.setStackInSlot(OUTPUT_SLOT, toAdd);
        setChanged();
    }

    private boolean getWorking(ItemStack slot) {
        if (type == null)
            return false;
        if (fuel.getEnergyStored() < Config.WATER_PER_CYCLE.get())
            return false;
        if (slot.getCount() == 0)
            return true;
        if (slot.getCount() + outputSize > slot.getMaxStackSize())
            return false;
        return slot.getItem().equals(type);
    }

    private boolean checkResource(int slot, Item blockItem, int singleValue, int maxCapacity, ResourceType type) {
        ItemStack stack = inventory.getStackInSlot(slot);
        int increment = stack.getItem().equals(blockItem) ? singleValue * 9 : singleValue;

        if (stack.isEmpty() || getResourceValue(type) + increment > maxCapacity)
            return false;

        stack.shrink(1);
        inventory.setStackInSlot(slot, stack);
        addResource(type, increment);
        return true;
    }

    private void addResource(ResourceType type, int amount) {
        switch (type) {
            case FUEL -> fuel.receiveEnergy(amount, false);
            case FERT -> fertilizer += amount;
            case POWER -> redstone += amount;
        }
    }

    public int soundCooldown = 0;

    private void playSound() {
        if (soundCooldown > 0) {
            soundCooldown--;
            return;
        }
        soundCooldown += 25;
        level.playSound(null, getBlockPos(), ModSounds.WORK_SOUND.get(), SoundSource.BLOCKS);
    }

    private int getResourceValue(ResourceType type) {
        return switch (type) {
            case FUEL -> fuel.getEnergyStored();
            case FERT -> fertilizer;
            case POWER -> redstone;
        };
    }

    private int getOutputSize() {
        return getOutputSize(type);
    }

    public static int getOutputSize(Item item) {
        if (item == null)
            return 1;

        if (item.equals(Items.SAND) || item.equals(Items.STONE) || item.equals(Items.GRAVEL))
            return 8;

        if (item.equals(Items.COAL_ORE) || item.equals(Items.DEEPSLATE_COAL_ORE)
                || item.equals(Items.COPPER_ORE) || item.equals(Items.DEEPSLATE_COPPER_ORE)
                || item.equals(Items.NETHER_QUARTZ_ORE))
            return 2;

        return 1;
    }

    private void checkNewType() {
        Item newType = getCurrentFilter();
        if (type == null && newType == null || type != null && type.equals(newType))
            return;

        type = newType;
        progress = 0;
        outputSize = getOutputSize();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        saveAll(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        saveAll(tag);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        type = getCurrentFilter();
        fuel = new EnergyStorage(Config.POWER_MAX.get(), Config.POWER_MAX.get(), Config.POWER_MAX.get(),
                tag.getInt("fuel"));
        progress = tag.getFloat("progress");
        fertilizer = tag.getInt("coolant");
        redstone = tag.getInt("redstone");
        outputSize = getOutputSize();
    }

    private void saveAll(CompoundTag tag) {
        tag.putInt("fuel", fuel.getEnergyStored());
        tag.putFloat("progress", progress);
        tag.putInt("coolant", fertilizer);
        tag.putInt("redstone", redstone);
    }

    private Item getCurrentFilter() {
        ItemStack stack = inventory.getStackInSlot(TYPE_SLOT);
        return stack.isEmpty() ? null : stack.getItem();
    }

    private enum ResourceType {
        FUEL, FERT, POWER
    }
}
