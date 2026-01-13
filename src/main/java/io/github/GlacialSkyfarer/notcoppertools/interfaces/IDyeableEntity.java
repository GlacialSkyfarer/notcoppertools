package io.github.GlacialSkyfarer.notcoppertools.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface IDyeableEntity {
    default void notcoppertools$setColor(ItemStack stack, LivingEntity entity) {}
    default int notcoppertools$getColor() { return 0; }
}
