package io.github.GlacialSkyfarer.notcoppertools.mixin.entity;

import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.interfaces.IDyeableEntity;
import io.github.GlacialSkyfarer.notcoppertools.item.GammaDyeItem;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin implements IDyeableEntity {

    @Shadow
    public abstract boolean isSheared();

    @Shadow
    public abstract int getColor();

    @Shadow
    public abstract void setColor(int color);

    @Shadow
    protected abstract int getDroppedItemId();

    @Override
    public void notcoppertools$setColor(ItemStack stack, LivingEntity entity) {
        if (!(stack.getItem() instanceof GammaDyeItem dye)) return;
        int color = 15 - dye.getColorId();
        if (!this.isSheared() && this.notcoppertools$getColor() != color) {
            this.setColor(color);
            stack.count--;
        }
    }

    @Inject(method="getDroppedItemId", at=@At("RETURN"), cancellable = true)
    void overrideDroppedItemId(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(switch (getColor()) {
                case 0 -> Blocks.WHITE_WOOL.asItem().id;
                case 1 -> Blocks.ORANGE_WOOL.asItem().id;
                case 2 -> Blocks.MAGENTA_WOOL.asItem().id;
                case 3 -> Blocks.LIGHT_BLUE_WOOL.asItem().id;
                case 4 -> Blocks.YELLOW_WOOL.asItem().id;
                case 5 -> Blocks.LIME_WOOL.asItem().id;
                case 6 -> Blocks.PINK_WOOL.asItem().id;
                case 7 -> Blocks.GRAY_WOOL.asItem().id;
                case 8 -> Blocks.LIGHT_GRAY_WOOL.asItem().id;
                case 9 -> Blocks.CYAN_WOOL.asItem().id;
                case 10 -> Blocks.PURPLE_WOOL.asItem().id;
                case 11 -> Blocks.BLUE_WOOL.asItem().id;
                case 12 -> Blocks.BROWN_WOOL.asItem().id;
                case 13 -> Blocks.GREEN_WOOL.asItem().id;
                case 14 -> Blocks.RED_WOOL.asItem().id;
                case 15 -> Blocks.BLACK_WOOL.asItem().id;
                default -> throw new IllegalStateException("Unexpected value: " + getColor());
            });
    }
    @Inject(method="dropItems", at=@At("HEAD"), cancellable = true)
    void overrideDrops(CallbackInfo ci) {
        if (!this.isSheared()) {
            ((SheepEntity)(Object)this).dropItem(new ItemStack(this.getDroppedItemId(), 1, 0), 0.0F);
            ci.cancel();
        }
    }
    @ModifyArgs(method="interact", at=@At(value="INVOKE", target="Lnet/minecraft/item/ItemStack;<init>(III)V"))
    private void overrideShearedItem(Args args) {
        args.set(0, this.getDroppedItemId());
        args.set(2, 0);
    }

    @Override
    public int notcoppertools$getColor() {
        return getColor();
    }
}
