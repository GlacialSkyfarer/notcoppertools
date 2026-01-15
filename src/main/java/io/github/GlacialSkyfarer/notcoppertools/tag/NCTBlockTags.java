package io.github.GlacialSkyfarer.notcoppertools.tag;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;

public class NCTBlockTags {

    public static final TagKey<Block>
            FENCE_CONNECTED = of("fence_connected");

    private static TagKey<Block> of(String id) {
        return TagKey.of(BlockRegistry.KEY, NotCopperTools.NAMESPACE.id(id));
    }

}
