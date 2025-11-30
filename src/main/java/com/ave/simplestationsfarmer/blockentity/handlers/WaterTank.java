package com.ave.simplestationsfarmer.blockentity.handlers;

import com.ave.simplestationsfarmer.Config;

import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class WaterTank extends FluidTank {

    public static WaterTank create(int value) {
        WaterTank inst = new WaterTank(Config.WATER_MAX.get());
        inst.fill(value);
        return inst;
    }

    public WaterTank(int capacity) {
        super(capacity);
    }

    public void fill(int value) {
        super.fill(new FluidStack(Fluids.WATER, value), FluidAction.EXECUTE);
    }

    public void drain(IntValue value) {
        super.drain(value.get(), FluidAction.EXECUTE);
    }

    public void drain(int value) {
        super.drain(value, FluidAction.EXECUTE);
    }

    public float getPercent() {
        return (float) getFluidAmount() / getCapacity();
    }
}
