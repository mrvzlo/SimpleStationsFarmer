package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.Config;
import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.registrations.ModBlockEntities;
import com.ave.simplestationsfarmer.screen.TreeFarmStationMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class TreeFarmerBlockEntity extends BaseFarmerBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get() * 2;

    public TreeFarmerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TREE_FARMER_ENTITY.get(), pos, state, CropGroup.Tree);

        powerUsage = 6;
        fluidUsage = WaterUsage;
    }

    @Override
    public TreeFarmStationMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TreeFarmStationMenu(containerId, inventory, this);
    }
}
