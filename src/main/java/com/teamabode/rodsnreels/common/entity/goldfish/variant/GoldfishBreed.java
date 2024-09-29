package com.teamabode.rodsnreels.common.entity.goldfish.variant;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum GoldfishBreed implements StringIdentifiable {
    RANCHU(0, "ranchu"),
    COMET(1, "comet"),
    BUBBLE_EYE(2, "bubble_eye");

    private static final IntFunction<GoldfishBreed> BY_ID = ValueLists.createIdToValueFunction(GoldfishBreed::getId, values(), ValueLists.OutOfBoundsHandling.ZERO);

    private final int id;
    private final String name;

    GoldfishBreed(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static GoldfishBreed byId(int id) {
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
