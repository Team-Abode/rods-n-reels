package com.teamabode.rodsnreels.core.misc;

import net.minecraft.component.type.FoodComponent;

public class RNRFoodComponents {
    public static final FoodComponent SQUID = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1f) // POOR
            .build();

    public static final FoodComponent CALAMARI = new FoodComponent.Builder()
            .nutrition(7)
            .saturationModifier(0.8f) // GOOD
            .build();

    public static final FoodComponent GOLDFISH = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1f) // POOR
            .build();
}
