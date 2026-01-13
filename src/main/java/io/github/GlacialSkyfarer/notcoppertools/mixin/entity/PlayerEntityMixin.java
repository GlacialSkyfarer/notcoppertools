package io.github.GlacialSkyfarer.notcoppertools.mixin.entity;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method="closeHandledScreen", at=@At("HEAD"), cancellable = true)
    public void removeThisStupidMethod(CallbackInfo ci) {
        ci.cancel();
    }

}
