package com.teamabode.rodsnreels.core.tag;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class RNRBiomeTags {
    public static final TagKey<Biome> HAS_SUSPICIOUS_BLOCK = create("has_suspicious_block");

    public static final TagKey<Biome> COPPER_DIVING_BELL_HAS_STRUCTURE = create("has_structure/copper_diving_bell");
    public static final TagKey<Biome> TRENCH_RUINS_HAS_STRUCTURE = create("has_structure/trench_ruins");

    private static TagKey<Biome> create(String id) {
        return TagKey.of(RegistryKeys.BIOME, RodsNReels.id(id));
    }
}
