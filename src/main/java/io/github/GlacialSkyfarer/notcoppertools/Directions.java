package io.github.GlacialSkyfarer.notcoppertools;

import net.modificationstation.stationapi.api.util.math.Direction;

public abstract class Directions {

    public static final Direction NORTH = Direction.EAST;
    public static final Direction SOUTH = Direction.WEST;
    public static final Direction EAST = Direction.SOUTH;
    public static final Direction WEST = Direction.NORTH;

    public static Direction toModern(Direction direction) { return direction.rotateYClockwise(); }

}
