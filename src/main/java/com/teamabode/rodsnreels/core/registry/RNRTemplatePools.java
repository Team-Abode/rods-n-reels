package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class RNRTemplatePools {

    public static final ResourceKey<StructureTemplatePool> TRENCH_RUINS_GATEWAYS = createKey("trench_ruins/gateways");
    public static final ResourceKey<StructureTemplatePool> TRENCH_RUINS_PILLARS = createKey("trench_ruins/pillars");


    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, RodsNReels.id(name));
    }
}
