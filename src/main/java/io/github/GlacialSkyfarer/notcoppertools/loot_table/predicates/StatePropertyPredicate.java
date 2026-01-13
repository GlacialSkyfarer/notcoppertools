package io.github.GlacialSkyfarer.notcoppertools.loot_table.predicates;

import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Property;

import java.util.Random;

public class StatePropertyPredicate<T extends Comparable<T>> implements IBlockLootPredicate {

    private final String property;
    private final T value;

    public StatePropertyPredicate(String property, T value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public boolean isFulfilled(BlockState state, Random random) {

        if(state.isAir()) return false;
        for(Property<?> p : state.getProperties()) {
            if (p.getType() == value.getClass() && p.getName().equals(property)) {

                return state.get(p) == value;

            }
        }

        return false;

    }
}
