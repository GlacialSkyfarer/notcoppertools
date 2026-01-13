package io.github.GlacialSkyfarer.gamma173.mixin.entity;

import io.github.GlacialSkyfarer.gamma173.item.Items;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SquidEntity.class)
public abstract class SquidEntityMixin {

    @Redirect(method="dropItems", at=@At(target = "Lnet/minecraft/item/Item;DYE:Lnet/minecraft/item/Item;", value = "FIELD", opcode = Opcodes.GETSTATIC))
    Item overrideDrops() {
        return Items.INK_SAC;
    }
}
