package com.teamabode.rodsnreels.common.entity.goldfish.variant;

import net.minecraft.entity.EntityData;

public class GoldfishData implements EntityData {
    public final GoldfishBreed breed;
    public final GoldfishColor color;

    public GoldfishData(GoldfishBreed breed, GoldfishColor color) {
        this.breed = breed;
        this.color = color;
    }
}
