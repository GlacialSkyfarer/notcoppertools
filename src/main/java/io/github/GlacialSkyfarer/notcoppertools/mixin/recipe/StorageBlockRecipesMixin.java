package io.github.GlacialSkyfarer.notcoppertools.mixin.recipe;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.DyeRecipes;
import net.minecraft.recipe.StorageBlockRecipes;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

import static io.github.GlacialSkyfarer.notcoppertools.NotCopperTools.LOGGER;

@Mixin(StorageBlockRecipes.class)
public class StorageBlockRecipesMixin {
    @Shadow
    private Object[][] items;

    @Inject(method = "add", at = @At("HEAD"))
    private void removeLapis(CallbackInfo ci) {
        if (!NotCopperTools.CONFIG.removeVanillaRecipes) return;
        for (int i = 0; i < items.length; i++) {
            if (((ItemStack)items[i][1]).itemId == Item.DYE.id) {
                items = ArrayUtils.remove(items, i);
            }
        }
    }

}
