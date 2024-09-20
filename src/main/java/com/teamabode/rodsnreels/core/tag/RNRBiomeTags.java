package com.teamabode.rodsnreels.core.tag;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class RNRBiomeTags {
    public static final TagKey<Biome> HAS_SUSPICIOUS_BLOCK = create("has_suspicious_block");

    public static final TagKey<Biome> HAS_COPPER_DIVING_BELL = create("has_structure/copper_diving_bell");
    public static final TagKey<Biome> HAS_TRENCH_RUINS = create("has_structure/trench_ruins");

    private static TagKey<Biome> create(String id) {
        return TagKey.create(Registries.BIOME, RodsNReels.id(id));
    }
}
