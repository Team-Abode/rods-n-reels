package com.teamabode.rodsnreels.core.tag;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class RNRBiomeTags {
    public static final TagKey<Biome> HAS_RIVER_ARCHAEOLOGY = create("has_river_archaeology");

    public static final TagKey<Biome> COPPER_DIVING_BELL_HAS_STRUCTURE = create("has_structure/copper_diving_bell");
    public static final TagKey<Biome> TRENCH_RUINS_HAS_STRUCTURE = create("has_structure/trench_ruins");

    public static final TagKey<Biome> SWEET_BERRIES_HAS_FISHING_JUNK = create("has_fishing_junk/sweet_berries");
    public static final TagKey<Biome> MANGROVE_PROPAGULE_HAS_FISHING_JUNK = create("has_fishing_junk/mangrove_propagule");

    private static TagKey<Biome> create(String id) {
        return TagKey.of(RegistryKeys.BIOME, RodsNReels.id(id));
    }
}
