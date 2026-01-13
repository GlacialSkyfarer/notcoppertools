package io.github.GlacialSkyfarer.notcoppertools.mixin.block;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Block.class)
public class BlockMixin {


    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", target="net/minecraft/block/ObsidianBlock.setHardness (F)Lnet/minecraft/block/Block;"), index = 0)
    private static float overrideObsidianHardness(float original) {

        return 40.0f;

    }

}
