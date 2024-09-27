package com.teamabode.rodsnreels.common.entity.goldfish.variant;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum GoldfishColor implements StringIdentifiable {
    ORANGE(0, "orange");

    private static final IntFunction<GoldfishColor> BY_ID = ValueLists.createIdToValueFunction(GoldfishColor::getId, values(), ValueLists.OutOfBoundsHandling.ZERO);

    private final int id;
    private final String name;

    GoldfishColor(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static GoldfishColor byId(int id) {
        return BY_ID.apply(id);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
