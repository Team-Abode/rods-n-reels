package com.teamabode.rodsnreels.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.structure.TrenchRuinsStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class RNRStructureTypes {
    public static final StructureType<TrenchRuinsStructure> TRENCH_RUINS = register("trench_ruins", TrenchRuinsStructure.CODEC);

    public static void register() {}

    private static <T extends Structure> StructureType<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.STRUCTURE_TYPE, RodsNReels.id(name), () -> codec);
    }
}
