package io.github.GlacialSkyfarer.gamma173.mixin.feature;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.gamma173.block.Blocks;
import io.github.GlacialSkyfarer.gamma173.block.GammaLeavesBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.SpruceTreeFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(SpruceTreeFeature.class)
public abstract class SpruceTreeFeatureMixin {

    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIIII)Z", ordinal=1))
    public void swapLogId(Args args) {

        args.set(3, Blocks.SPRUCE_LOG.id);
        args.set(4, 0);

    }
    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIIII)Z", ordinal=0))
    public void swapLeavesId(Args args) {

        args.set(3, Blocks.SPRUCE_LOG.id);
        args.set(4, 0);

    }
//    @Inject(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z", ordinal=0, shift = At.Shift.AFTER))
//    public void setLeavesDistance(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 7) int blockX, @Local(ordinal = 9) int blockY, @Local(ordinal = 11) int blockZ) {
//
//        world.setBlockState(blockX,blockY,blockZ,world.getBlockState(blockX,blockY,blockZ).with(GammaLeavesBlock.DISTANCE, Math.abs(x-blockX) + Math.abs(z-blockZ)));
//
//    }
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=0, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck(LeavesBlock instance) {
        return Blocks.SPRUCE_LEAVES.id;
    }

    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=2, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck2(LeavesBlock instance) {
        return Blocks.SPRUCE_LEAVES.id;
    }

}
