package io.github.GlacialSkyfarer.gamma173.mixin.feature;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.gamma173.block.Blocks;
import io.github.GlacialSkyfarer.gamma173.block.GammaLeavesBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BirchTreeFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(BirchTreeFeature.class)
public abstract class BirchTreeFeatureMixin {

    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIIII)Z", ordinal=1))
    public void swapLogId(Args args) {

        args.set(3, Blocks.BIRCH_LOG.id);
        args.set(4, 0);

    }
    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIIII)Z", ordinal=0))
    public void swapLeavesId(Args args) {

        args.set(3, Blocks.BIRCH_LEAVES.id);
        args.set(4, 0);

    }
//    @Inject(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z", ordinal=0, shift = At.Shift.AFTER))
//    public void setLeavesDistance(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 3) int blockX, @Local(ordinal = 6) int blockY, @Local(ordinal = 8) int blockZ) {
//
//        world.setBlockState(blockX,blockY,blockZ,world.getBlockState(blockX,blockY,blockZ).with(GammaLeavesBlock.DISTANCE, Math.abs(x-blockX) + Math.abs(z-blockZ)));
//
//    }
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=0, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck(LeavesBlock instance) {
        return Blocks.BIRCH_LEAVES.id;
    }

    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=2, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck2(LeavesBlock instance) {
        return Blocks.BIRCH_LEAVES.id;
    }

}
