package com.ave.simplestationsfarmer.blockentity;

import com.ave.simplestationsfarmer.blockentity.enums.CropGroup;
import com.ave.simplestationsfarmer.blockentity.handlers.CommonItemHandler;
import com.ave.simplestationsfarmer.blockentity.handlers.SidedItemHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

public abstract class ModContainer extends BlockEntity implements MenuProvider {
    public final CommonItemHandler inventory;
    public static final int OUTPUT_SLOT = 0;
    public static final int FLUID_SLOT = 1;
    public static final int TYPE_SLOT = 2;
    public static final int FERTI_SLOT = 3;
    public static final int REDSTONE_SLOT = 4;

    public ModContainer(BlockEntityType<BlockEntity> entity, BlockPos pos, BlockState state, int size,
            CropGroup group) {
        super(entity, pos, state);
        inventory = new CommonItemHandler(size, group) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.simplestationsfarmer.farmer");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    public IItemHandler getInventory(Direction dir) {
        if (dir == null)
            return inventory;
        return new SidedItemHandler(inventory);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
