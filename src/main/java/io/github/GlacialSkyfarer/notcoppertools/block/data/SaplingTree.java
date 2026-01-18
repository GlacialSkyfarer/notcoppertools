package io.github.GlacialSkyfarer.notcoppertools.block.data;

import net.minecraft.world.gen.feature.Feature;

public class SaplingTree {
    public SaplingTree(Feature feature, int weight) {
        this.weight = weight;
        this.feature = feature;
    }
    public int weight = 1;
    public Feature feature;
}
