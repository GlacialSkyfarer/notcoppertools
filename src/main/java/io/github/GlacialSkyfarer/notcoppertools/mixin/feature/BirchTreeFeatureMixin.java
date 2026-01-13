package io.github.GlacialSkyfarer.notcoppertools.mixin.feature;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.block.GammaLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BirchTreeFeature;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
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
    @Inject(method="generate", at = @At(value="RETURN"))
    public void updateTipLeaf(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(index=6) int yOffset) {
        if (cir.getReturnValue()) {
            Block block = world.getBlockState(x, y+yOffset, z).getBlock();
            if (block instanceof GammaLeavesBlock template) {
                template.updateDistance(world, new BlockPos(x,y+yOffset,z), false);
            }
        }
    }
    @WrapOperation(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I", ordinal=0))
    private int swapLeavesCheck(World world, int x, int y, int z, Operation<Integer> original) {
        return world.getBlockState(x,y,z).isIn(BlockTags.LEAVES) ? 0 : original.call(world, x, y, z);
    }

    @WrapOperation(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I", ordinal=3))
    private int swapLeavesCheck2(World world, int x, int y, int z, Operation<Integer> original) {
        return world.getBlockState(x,y,z).isIn(BlockTags.LEAVES) ? 0 : original.call(world, x, y, z);
    }


}
