package io.github.GlacialSkyfarer.gamma173.mixin.recipe;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.FoodRecipes;
import net.minecraft.recipe.SmeltingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodRecipes.class)
public class FoodRecipesMixin {

    @WrapOperation(method = "add", at = @At(value="INVOKE", target="Lnet/minecraft/recipe/CraftingRecipeManager;addShapedRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V", ordinal=2))
    private void removeCookie(CraftingRecipeManager instance, ItemStack input, Object[] objects, Operation<Void> original) {}

}
