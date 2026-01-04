package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationscore.resources.FluidResource;
import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.handlers.FarmItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.OptionalEnergyResource;
import com.ave.simplestationsfarmer.recipes.ModRecipes;
import com.ave.simplestationsfarmer.registrations.Registrations;
import com.ave.simplestationsfarmer.screen.FarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class FarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get();

    public FarmerBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.FARMER.getEntity(), pos, state);

        resources.put(FUEL_SLOT, new OptionalEnergyResource(2));
        resources.put(FLUID_SLOT, new FluidResource(Fluids.WATER, Config.FLUID_MAX.get(), WaterUsage));

        inventory = new FarmItemHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public FarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FarmStationMenu(containerId, inventory, this);
    }

    public SoundEvent getWorkSound() {
        return SoundEvents.CROP_BREAK;
    }

    protected int getTypeBySeed(Item filter) {
        var type = ModRecipes.cropToInt.get(filter);
        return type == null ? -1 : type;
    }

    public ItemStack getStackByType(int type) {
        var recipe = ModRecipes.intToCropRecipe.get(type);
        return recipe == null ? ItemStack.EMPTY : recipe.to();
    }
}
