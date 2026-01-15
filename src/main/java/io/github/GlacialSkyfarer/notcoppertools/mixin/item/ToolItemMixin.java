package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ToolItem.class)
public abstract class ToolItemMixin extends Item implements IHasRepairMaterial {

    @Unique
    private Item repairItem = null;

    public ToolItemMixin(int id) {
        super(id);
    }

    @Override
    public Item notcoppertools$getRepairMaterial() {
        return repairItem;
    }
    @Override
    public Item notcoppertools$setRepairMaterial(Item material) {
        this.repairItem = material;
        return this;
    }
}
