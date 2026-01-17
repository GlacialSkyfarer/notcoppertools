package io.github.GlacialSkyfarer.notcoppertools.mixin.recipe;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SmeltingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SmeltingRecipeManager.class)
public class SmeltingRecipeManagerMixin {

    @WrapOperation(method = "<init>", at = @At(value="INVOKE", target="Lnet/minecraft/recipe/SmeltingRecipeManager;addRecipe(ILnet/minecraft/item/ItemStack;)V"))
    private void removeCactusGreen(SmeltingRecipeManager instance, int output, ItemStack itemStack, Operation<Void> original) {
        if (output != Block.CACTUS.id) {
            original.call(instance, output, itemStack);
        }
    }

}
