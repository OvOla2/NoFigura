package org.figuramc.figura.compat;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;

import java.util.concurrent.atomic.AtomicInteger;

/** Client-side EntityTask compatibility for 26.3 entity IDs and NBT. */
public final class EntityTaskCompat {

    private static final AtomicInteger FAKE_ENTITY_ID = new AtomicInteger(-1);

    private EntityTaskCompat() {
    }

    /** EntityTask entities are not in a level and need a non-zero ID on 26.3. */
    public static int nextFakeEntityId() {
        return FAKE_ENTITY_ID.getAndDecrement();
    }

    /** Adds the 26.3 block-attached fields when they are missing. */
    public static void addBlockAttachedDefaults(CompoundTag nbt) {
        if (!nbt.contains("block_pos"))
            nbt.put("block_pos", new IntArrayTag(new int[]{0, 0, 0}));
        if (!nbt.contains("facing"))
            nbt.putByte("facing", (byte) Direction.SOUTH.get2DDataValue());
    }
}
