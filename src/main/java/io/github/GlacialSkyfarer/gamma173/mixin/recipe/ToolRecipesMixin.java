package io.github.GlacialSkyfarer.gamma173.mixin.recipe;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.gamma173.Gamma173;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ToolRecipes;
import net.modificationstation.stationapi.api.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToolRecipes.class)
public class ToolRecipesMixin {
    @WrapOperation(method = "add", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipeManager;addShapedRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V", ordinal = 0))
    void removeStone(CraftingRecipeManager instance, ItemStack input, Object[] objects, Operation<Void> original, @Local Object material) {
        if (!Gamma173.CONFIG.removeVanillaRecipes || material != Block.COBBLESTONE) {
            original.call(instance, input, objects);
        }
    }

}
