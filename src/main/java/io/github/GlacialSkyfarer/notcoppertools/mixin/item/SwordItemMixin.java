package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends Item implements IHasRepairMaterial {

    @Unique
    private Item repairItem = null;

    public SwordItemMixin(int id) {
        super(id);
    }

    @Override
    public Item gamma_173$getRepairMaterial() {
        return repairItem;
    }
    @Override
    public Item gamma_173$setRepairMaterial(Item material) {
        this.repairItem = material;
        return this;
    }
}
