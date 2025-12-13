package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.enums.CropType;
import com.ave.simplestationsfarmer.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.OutputItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.WaterTank;
import com.ave.simplestationsfarmer.blockentity.managers.ExportManager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class BaseFarmerBlockEntity extends ModContainer {
    public EnergyStorage fuel = new EnergyStorage(Config.POWER_MAX.get());
    public int fuelHigh = 0;
    public int fuelLow = 0;
    public WaterTank tank = WaterTank.create(0);
    public CropType type = CropType.Unknown;
    public float progress = 0;
    public int fertilizer = 0;
    public boolean working = false;

    protected int powerUsage = 1;
    protected int speed = 1;
    public int fluidUsage;

    public BaseFarmerBlockEntity(BlockEntityType entity, BlockPos pos, BlockState state, CropGroup group) {
        super(entity, pos, state, 5, group);
    }

    public void tick() {
        if (level.isClientSide)
            return;

        if (progress >= Config.MAX_PROGRESS.get())
            progress -= Config.MAX_PROGRESS.get();

        checkNewType();
        checkResource(FLUID_SLOT, null, 1000, Config.WATER_MAX.get(), ResourceType.FLUID);
        checkResource(REDSTONE_SLOT, Items.REDSTONE_BLOCK, Config.POWER_PER_RED.get(), Config.POWER_MAX.get(),
                ResourceType.POWER);
        checkResource(FERTI_SLOT, null, Config.FERT_PER_ITEM.get(), Config.FERT_MAX.get(),
                ResourceType.FERT);

        var slot = inventory.getStackInSlot(OUTPUT_SLOT);
        working = getWorking(slot);
        ExportManager.pushOutput(this);

        fuelHigh = fuel.getEnergyStored() >> 16;
        fuelLow = fuel.getEnergyStored() & 0xFFFF;
        if (!working)
            return;

        progress += speed;

        if (fertilizer > 0) {
            fertilizer--;
            progress += Config.FERT_MULT.get();
        }
        if (fuel.getEnergyStored() >= powerUsage) {
            fuel.extractEnergy(powerUsage, false);
            progress += Config.POWER_MULT.get();
        }
        playSound();

        if (progress < Config.MAX_PROGRESS.get())
            return;

        tank.drain(fluidUsage);
        var toAdd = new ItemStack(type.getProduct());
        toAdd.setCount(slot.getCount() + type.output);
        inventory.setStackInSlot(OUTPUT_SLOT, toAdd);
        setChanged();
    }

    private boolean getWorking(ItemStack slot) {
        if (type == null || type == CropType.Unknown)
            return false;
        if (tank.getFluidAmount() < fluidUsage)
            return false;
        if (slot.getCount() == 0)
            return true;
        if (slot.getCount() + type.output > slot.getMaxStackSize())
            return false;
        return slot.getItem().equals(type.product);
    }

    private void checkResource(int slot, Item blockItem, int increment, int maxCapacity, ResourceType type) {
        var stack = inventory.getStackInSlot(slot);

        if (blockItem != null && stack.getItem().equals(blockItem))
            increment *= 9;

        if (stack.isEmpty() || stack.getItem().equals(Items.BUCKET) || getResourceValue(type) + increment > maxCapacity)
            return;

        if (stack.getItem().equals(Items.WATER_BUCKET) || stack.getItem().equals(Items.LAVA_BUCKET))
            inventory.setStackInSlot(slot, new ItemStack(Items.BUCKET, 1));
        else {
            stack.shrink(1);
            inventory.setStackInSlot(slot, stack);
        }
        addResource(type, increment);
    }

    private void addResource(ResourceType type, int amount) {
        switch (type) {
            case FLUID -> tank.fill(amount);
            case FERT -> fertilizer += amount;
            case POWER -> fuel.receiveEnergy(amount, false);
        }
    }

    public int soundCooldown = 0;

    private void playSound() {
        if (soundCooldown > 0) {
            soundCooldown--;
            return;
        }
        soundCooldown += 100;
        level.playSound(null, getBlockPos(), getSound(), SoundSource.BLOCKS);
    }

    protected SoundEvent getSound() {
        return SoundEvents.CROP_BREAK;
    }

    private int getResourceValue(ResourceType type) {
        return switch (type) {
            case FLUID -> tank.getFluidAmount();
            case FERT -> fertilizer;
            case POWER -> fuel.getEnergyStored();
        };
    }

    private void checkNewType() {
        var newType = getCurrentFilter();
        if (type == null && newType == null || type != null && type.equals(newType))
            return;

        type = newType;
        progress = 0;
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> this.fuel);
    private final LazyOptional<IFluidHandler> fluid = LazyOptional.of(() -> this.tank);
    private final LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> new OutputItemHandler(inventory));
    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> new InputItemHandler(inventory));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY)
            return energy.cast();
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            return fluid.cast();
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return side.equals(Direction.DOWN) ? outputHandler.cast() : inputHandler.cast();

        return super.getCapability(cap, side);
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
        type = CropType.findById(tag.getInt("type"));
        fuel = new EnergyStorage(Config.POWER_MAX.get(), Config.POWER_MAX.get(), Config.POWER_MAX.get(),
                tag.getInt("fuel"));
        progress = tag.getFloat("progress");
        fertilizer = tag.getInt("fertilizer");
        tank = WaterTank.create(tag.getInt("water"));
    }

    private void saveAll(CompoundTag tag) {
        tag.putInt("fuel", fuel.getEnergyStored());
        tag.putFloat("progress", progress);
        tag.putInt("fertilizer", fertilizer);
        tag.putInt("water", tank.getFluidAmount());
        if (type != null)
            tag.putInt("type", type.ordinal());
    }

    private CropType getCurrentFilter() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        return stack.isEmpty() ? CropType.Unknown : CropType.findBySeed(stack.getItem());
    }

    private enum ResourceType {
        FLUID, FERT, POWER
    }
}
