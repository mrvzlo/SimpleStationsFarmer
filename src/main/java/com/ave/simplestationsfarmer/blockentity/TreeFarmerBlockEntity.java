package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.resources.EnergyResource;
import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalEnergyResource;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.TreeFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class TreeFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get() * 2;

    public TreeFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.TREE_FARMER.getEntity(), pos, state, CropGroup.Tree);

        fluidUsage = WaterUsage;
        resources.put(FUEL_SLOT, new OptionalEnergyResource(6));
        resources.put(FLUID_SLOT, new FluidResource(Fluids.WATER, Config.FLUID_MAX.get(), WaterUsage));
    }

    @Override
    public TreeFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TreeFarmStationMenu(containerId, inventory, this);
    }

    public SoundEvent getWorkSound() {
        return SoundEvents.WOOD_BREAK;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                Registrations.TREE_FARMER.getEntity(),
                (be, d) -> be.getItemHandler(d));
    }
}
