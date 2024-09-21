package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;

public class RNRTemplatePools {
    public static final RegistryKey<StructurePool> TRENCH_RUINS_GATEWAYS = createKey("trench_ruins/gateways");
    public static final RegistryKey<StructurePool> TRENCH_RUINS_PILLARS = createKey("trench_ruins/pillars");

    private static RegistryKey<StructurePool> createKey(String name) {
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, RodsNReels.id(name));
    }
}
