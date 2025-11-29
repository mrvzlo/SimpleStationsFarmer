package com.ave.simplestationsfarmer.blockentity.enums;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum CropType implements StringRepresentable {
    Unknown(CropGroup.Unknown, null, 0),
    WHEAT(CropGroup.Crop, Items.WHEAT_SEEDS, Items.WHEAT, 32),
    BEETROOT(CropGroup.Crop, Items.BEETROOT_SEEDS, Items.BEETROOT, 32),
    CARROT(CropGroup.Crop, Items.CARROT, 32),
    POTATO(CropGroup.Crop, Items.POTATO, 32),
    SUGAR(CropGroup.Crop, Items.SUGAR_CANE, 64),
    BERRY(CropGroup.Crop, Items.SWEET_BERRIES, 64),
    MELON(CropGroup.Crop, Items.MELON_SEEDS, Items.MELON, 16),
    PUMPKIN(CropGroup.Crop, Items.PUMPKIN_SEEDS, Items.PUMPKIN, 16),
    CACTUS(CropGroup.Crop, Items.CACTUS, 32),
    GLOWBERRY(CropGroup.Crop, Items.GLOW_BERRIES, 64),
    BROWN_MUSHROOM(CropGroup.Dark, Items.BROWN_MUSHROOM, 32),
    RED_MUSHROOM(CropGroup.Dark, Items.RED_MUSHROOM, 32),
    NETHER_WART(CropGroup.Dark, Items.NETHER_WART, 64),
    CHORUS(CropGroup.Dark, Items.CHORUS_FRUIT, 16);

    public final Item seed;
    public final Item product;
    public final int output;
    public final CropGroup group;

    CropType(CropGroup group, Item seed, int output) {
        this.seed = seed;
        this.group = group;
        this.product = seed;
        this.output = output;
    }

    CropType(CropGroup group, Item seed, Item product, int output) {
        this.seed = seed;
        this.product = product;
        this.output = output;
        this.group = group;
    }

    public static CropType findById(int type) {
        return CropType.values()[type];
    }

    public static CropType findBySeed(Item stack) {
        for (var c : values())
            if (stack.equals(c.seed))
                return c;

        return CropType.Unknown;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
