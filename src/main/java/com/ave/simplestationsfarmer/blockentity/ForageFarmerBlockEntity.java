package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.handlers.ForagableItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalEnergyResource;
import com.ave.simplestationsfarmer.recipes.ModRecipes;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.ForageFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class ForageFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get() / 10;

    public ForageFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.FORAGE_FARMER.getEntity(), pos, state);

        speed = 5;
        resources.put(FUEL_SLOT, new OptionalEnergyResource(1));
        resources.put(FLUID_SLOT, new FluidResource(Fluids.WATER, Config.FLUID_MAX.get(), WaterUsage));

        inventory = new ForagableItemHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public ForageFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ForageFarmStationMenu(containerId, inventory, this);
    }

    public SoundEvent getWorkSound() {
        return SoundEvents.FLOWERING_AZALEA_BREAK;
    }

    protected int getTypeBySeed(Item filter) {
        var type = ModRecipes.foragableToInt.get(filter);
        return type == null ? -1 : type;
    }
}
