package com.teamabode.rodsnreels.core.misc;

import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;

public class RNRFoodProperties {
    public static final FoodProperties RAW_SQUID = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(FoodConstants.FOOD_SATURATION_POOR)
            .build();

    public static final FoodProperties CALAMARI = new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(FoodConstants.FOOD_SATURATION_GOOD)
            .build();
}
