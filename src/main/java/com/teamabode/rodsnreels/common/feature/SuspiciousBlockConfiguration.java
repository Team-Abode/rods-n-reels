package com.teamabode.rodsnreels.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.storage.loot.LootTable;

public record SuspiciousBlockConfiguration(BlockStateProvider toPlace, ResourceKey<LootTable> lootTable) implements FeatureConfiguration {
    public static final Codec<SuspiciousBlockConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("to_place").forGetter(SuspiciousBlockConfiguration::toPlace),
            ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("loot_table").forGetter(SuspiciousBlockConfiguration::lootTable)
    ).apply(instance, SuspiciousBlockConfiguration::new));
}
