package io.github.GlacialSkyfarer.gamma173.mixin.recipe;

import io.github.GlacialSkyfarer.gamma173.Gamma173;
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
        if (Gamma173.CONFIG.removeVanillaRecipes) ci.cancel();
    }

}
