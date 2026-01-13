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
import net.minecraft.world.gen.feature.LargeOakTreeFeature;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(LargeOakTreeFeature.class)
public abstract class LargeOakTreeFeatureMixin {

    @Shadow
    World world;

    @Shadow
    int height;

    @ModifyArgs(method = "placeCluster", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z"))
    public void swapLeavesId(Args args) {

        args.set(3, Blocks.OAK_LEAVES.id);

    }
    @ModifyConstant(method = "placeCluster", constant = @Constant(intValue=18))
    public int swapLeavesCheck(int constant, @Local(ordinal=1) int[] position) {
        return this.world.getBlockState(position[0], position[1], position[2]).isIn(BlockTags.LEAVES) ? 0 : constant;
    }
    @ModifyArgs(method = "placeBranch", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z"))
    public void swapLogId(Args args) {

        args.set(3, Blocks.OAK_LOG.id);

    }
    @Inject(method="generate", at = @At(value="RETURN"))
    public void updateTipLeaf(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            Block block = world.getBlockState(x, y+this.height, z).getBlock();
            if (block instanceof GammaLeavesBlock template) {
                template.updateDistance(world, new BlockPos(x,y+this.height,z), false);
            }
        }
    }
    @WrapOperation(method = "placeCluster", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    private int swapLeavesCheck(World world, int x, int y, int z, Operation<Integer> original) {
        return world.getBlockState(x,y,z).isIn(BlockTags.LEAVES) ? 0 : original.call(world, x, y, z);
    }

    @WrapOperation(method = "tryBranch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    private int swapLeavesCheck2(World world, int x, int y, int z, Operation<Integer> original) {
        return world.getBlockState(x,y,z).isIn(BlockTags.LEAVES) ? 0 : original.call(world, x, y, z);
    }


}
