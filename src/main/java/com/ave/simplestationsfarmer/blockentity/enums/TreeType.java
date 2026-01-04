package com.ave.simplestationsfarmer.blockentity.enums;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum TreeType implements StringRepresentable {
    ACACIA(Items.ACACIA_SAPLING, Items.ACACIA_LOG),
    BIRCH(Items.BIRCH_SAPLING, Items.BIRCH_LOG),
    CHERRY(Items.CHERRY_SAPLING, Items.CHERRY_LOG),
    CR_STEM(Items.CRIMSON_FUNGUS, Items.CRIMSON_STEM),
    DARK_OAK(Items.DARK_OAK_SAPLING, Items.DARK_OAK_LOG),
    JUNGLE(Items.JUNGLE_SAPLING, Items.JUNGLE_LOG),
    MANGROVE(Items.MANGROVE_PROPAGULE, Items.MANGROVE_LOG),
    OAK(Items.OAK_SAPLING, Items.OAK_LOG),
    SPRUCE(Items.SPRUCE_SAPLING, Items.SPRUCE_LOG),
    WD_STEM(Items.WARPED_FUNGUS, Items.WARPED_STEM),
    BAMBOO(Items.BAMBOO, 64);

    public final Item seed;
    public final Item product;
    protected int output = 32;

    TreeType(Item seed, Item product) {
        this.seed = seed;
        this.product = product;
    }

    TreeType(Item seed, int output) {
        this.seed = seed;
        this.product = seed;
        this.output = output;
    }

    public static TreeType findById(int type) {
        if (type == -1 || type >= TreeType.values().length)
            return null;
        return TreeType.values()[type];
    }

    public static TreeType findBySeed(Item stack) {
        for (var c : values()) {
            if (c.seed != null && stack.equals(c.seed))
                return c;
        }

        return null;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

    public ItemStack getProduct() {
        return new ItemStack(product, output);
    }
}
