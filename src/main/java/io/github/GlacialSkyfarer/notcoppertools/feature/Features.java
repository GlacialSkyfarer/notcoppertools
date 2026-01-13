package io.github.GlacialSkyfarer.notcoppertools.feature;

import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.modificationstation.stationapi.api.worldgen.feature.VolumetricScatterFeature;

public class Features {
    public static final Feature COPPER_ORE = new OreFeature(Blocks.COPPER_ORE.id, 16);
    public static final Feature COPPER_ORE_SCATTERED = new VolumetricScatterFeature(COPPER_ORE, 20, 128);
}
