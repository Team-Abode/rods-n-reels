package com.teamabode.rodsnreels.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record SuspiciousBlockConfig(BlockStateProvider toPlace, RegistryKey<LootTable> lootTable) implements FeatureConfig {
    public static final Codec<SuspiciousBlockConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("to_place").forGetter(SuspiciousBlockConfig::toPlace),
            RegistryKey.createCodec(RegistryKeys.LOOT_TABLE).fieldOf("loot_table").forGetter(SuspiciousBlockConfig::lootTable)
    ).apply(instance, SuspiciousBlockConfig::new));
}
