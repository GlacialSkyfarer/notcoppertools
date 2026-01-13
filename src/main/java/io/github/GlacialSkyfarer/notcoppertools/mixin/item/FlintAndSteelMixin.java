package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import net.minecraft.item.FlintAndSteel;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(FlintAndSteel.class)
public abstract class FlintAndSteelMixin extends Item implements IHasRepairMaterial {

    @Unique
    private Item repairItem = null;

    public FlintAndSteelMixin(int id) {
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
