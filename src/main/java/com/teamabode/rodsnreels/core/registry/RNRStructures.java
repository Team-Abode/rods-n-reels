package com.teamabode.rodsnreels.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.structure.TrenchRuinsStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class RNRStructures {
    public static final StructureType<TrenchRuinsStructure> TRENCH_RUINS = register("trench_ruins", TrenchRuinsStructure.CODEC);

    public static void register() {}

    private static <T extends Structure> StructureType<T> register(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, RodsNReels.id(name), () -> codec);
    }
}
