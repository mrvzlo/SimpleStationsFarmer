package com.ave.simplestationsfarmer.blockentity;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum CropType implements StringRepresentable {
    Unknown(null, 0),
    WHEAT(Items.WHEAT_SEEDS, Items.WHEAT, 32),
    BEETROOT(Items.BEETROOT_SEEDS, Items.BEETROOT, 32),
    CARROT(Items.CARROT, 32),
    POTATO(Items.POTATO, 32),
    SUGAR(Items.SUGAR_CANE, 64),
    BERRY(Items.SWEET_BERRIES, 64),
    MELON(Items.MELON_SEEDS, Items.MELON, 16),
    PUMPKIN(Items.PUMPKIN_SEEDS, Items.PUMPKIN, 16),
    CACTUS(Items.CACTUS, 32),
    GLOWBERRY(Items.GLOW_BERRIES, 64);

    public final Item seed;
    public final Item product;
    public final int output;

    CropType(Item seed, int output) {
        this.seed = seed;
        this.product = seed;
        this.output = output;
    }

    CropType(Item seed, Item product, int output) {
        this.seed = seed;
        this.product = product;
        this.output = output;
    }

    public static CropType fromSeed(Item stack) {
        for (var c : values()) {
            if (stack.equals(c.seed))
                return c;
        }
        return null;
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase();
    }
}
