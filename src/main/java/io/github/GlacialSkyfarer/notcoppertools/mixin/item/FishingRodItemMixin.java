package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin extends Item implements IHasRepairMaterial {

    @Unique
    private Item repairItem = null;

    public FishingRodItemMixin(int id) {
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
