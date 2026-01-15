package io.github.GlacialSkyfarer.notcoppertools.interfaces;

import net.minecraft.item.Item;

public interface IHasRepairMaterial {

    default Item notcoppertools$getRepairMaterial() { return null; }
    default Item notcoppertools$setRepairMaterial(Item material) {return null;}

}
