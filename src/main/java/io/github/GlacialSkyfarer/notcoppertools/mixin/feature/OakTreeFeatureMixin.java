package io.github.GlacialSkyfarer.notcoppertools.mixin.feature;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.block.NCTLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.OakTreeFeature;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(OakTreeFeature.class)
public abstract class OakTreeFeatureMixin {

    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z", ordinal=2))
    public void swapLogId(Args args) {

        args.set(3, Blocks.OAK_LOG.id);

    }
    @ModifyArgs(method = "generate", at = @At(value="INVOKE", target="Lnet/minecraft/world/World;setBlockWithoutNotifyingNeighbors(IIII)Z", ordinal=1))
    public void swapLeavesId(Args args) {

        args.set(3, Blocks.OAK_LEAVES.id);

    }
    @Inject(method="generate", at = @At(value="RETURN"))
    public void updateTipLeaf(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(index=6) int yOffset) {
        if (cir.getReturnValue()) {
            Block block = world.getBlockState(x, y+yOffset, z).getBlock();
            if (block instanceof NCTLeavesBlock template) {
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
