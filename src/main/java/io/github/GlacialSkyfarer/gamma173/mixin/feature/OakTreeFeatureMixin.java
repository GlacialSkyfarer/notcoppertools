package io.github.GlacialSkyfarer.gamma173.mixin.feature;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.gamma173.block.Blocks;
import io.github.GlacialSkyfarer.gamma173.block.GammaLeavesBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.OakTreeFeature;
import net.modificationstation.stationapi.api.util.math.MutableBlockPos;
import org.objectweb.asm.Opcodes;
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
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=0, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck(LeavesBlock instance) {
        return Blocks.OAK_LEAVES.id;
    }

    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/LeavesBlock;id:I", ordinal=2, opcode = Opcodes.GETFIELD))
    private int swapLeavesCheck2(LeavesBlock instance) {
        return Blocks.OAK_LEAVES.id;
    }

}
