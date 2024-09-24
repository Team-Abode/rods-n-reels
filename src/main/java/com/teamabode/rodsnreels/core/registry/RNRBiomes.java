package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

public class RNRBiomes {
    public static final RegistryKey<Biome> OCEAN_TRENCH = createKey("ocean_trench");

    private static Biome createOceanTrench(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers) {
        var builder = new Biome.Builder();

        var effects = new BiomeEffects.Builder();
        effects.fogColor(12638463);
        effects.moodSound(BiomeMoodSound.CAVE);
        effects.skyColor(8103167);
        effects.waterColor(2769069);
        effects.waterFogColor(197409);

        var generationSettings = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);

        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addWaterBiomeOakTrees(generationSettings);
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings);
        DefaultBiomeFeatures.addDefaultGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_DEEP);
        DefaultBiomeFeatures.addSeagrassOnStone(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, RNRPlacedFeatures.BUBBLEDEW);

        var spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addOceanMobs(spawnSettings, 10, 4, 10);
        spawnSettings.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 1, 1, 2));

        builder.generationSettings(generationSettings.build());
        builder.spawnSettings(spawnSettings.build());
        builder.effects(effects.build());

        builder.temperature(0.5f);
        builder.downfall(0.5f);
        builder.precipitation(true);
        return builder.build();
    }

    public static void register(Registerable<Biome> registry) {
        var placedFeatures = registry.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        var configuredCarvers = registry.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        registry.register(OCEAN_TRENCH, createOceanTrench(placedFeatures, configuredCarvers));
    }

    private static RegistryKey<Biome> createKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, RodsNReels.id(name));
    }
}
