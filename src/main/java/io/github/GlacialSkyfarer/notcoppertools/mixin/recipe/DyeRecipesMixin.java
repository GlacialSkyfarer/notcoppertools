package io.github.GlacialSkyfarer.notcoppertools.mixin.recipe;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.DyeRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DyeRecipes.class)
public class DyeRecipesMixin {
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    void interruptRegistering(CraftingRecipeManager par1, CallbackInfo ci) {
        if (NotCopperTools.CONFIG.removeVanillaRecipes) ci.cancel();
    }

}
