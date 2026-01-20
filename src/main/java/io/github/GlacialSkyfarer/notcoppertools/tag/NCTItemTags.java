package io.github.GlacialSkyfarer.notcoppertools.tag;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;

public class NCTItemTags {

    public static final TagKey<Item>
            LOGS_THAT_BURN = of("logs_that_burn"),
            PLANKS = of("planks"),
            WOODEN_STAIRS = of("wooden_stairs"),
            WOODEN_FENCES = of("wooden_fences"),
            WOODEN_SLABS = of("wooden_slabs"),
            WOODEN_TRAPDOORS = of("wooden_trapdoors"),
            WOODEN_DOORS = of("wooden_doors");

    private static TagKey<Item> of(String id) {
        return TagKey.of(ItemRegistry.KEY, NotCopperTools.NAMESPACE.id(id));
    }

}
