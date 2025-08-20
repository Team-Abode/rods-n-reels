package com.teamabode.rodsnreels.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.loot.EnchantedToolCountIncreaseLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNRLootFunctionTypes {
    public static final LootFunctionType<EnchantedToolCountIncreaseLootFunction> ENCHANTED_TOOL_COUNT_INCREASE = register("enchanted_tool_count_increase", EnchantedToolCountIncreaseLootFunction.CODEC);

    private static <T extends LootFunction> LootFunctionType<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.LOOT_FUNCTION_TYPE, RodsNReels.id(id), new LootFunctionType<>(codec));
    }
}
