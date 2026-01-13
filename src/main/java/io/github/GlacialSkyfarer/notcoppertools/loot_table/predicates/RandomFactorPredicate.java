package io.github.GlacialSkyfarer.notcoppertools.loot_table.predicates;

import net.modificationstation.stationapi.api.block.BlockState;

import java.util.Random;

public class RandomFactorPredicate implements IBlockLootPredicate {

    private double factor = 0;

    public RandomFactorPredicate(double factor) {
        this.factor = factor;
    }

    @Override
    public boolean isFulfilled(BlockState state, Random random) {

        if(state.isAir()) return false;
        return random.nextDouble(0, 1) < factor;

    }
}
