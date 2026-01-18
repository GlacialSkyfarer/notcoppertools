package io.github.GlacialSkyfarer.notcoppertools.mixin.chunk;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.datafixer.NCTDatafixer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.modificationstation.stationapi.impl.world.FlattenedWorldManager;
import net.modificationstation.stationapi.impl.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlattenedWorldManager.class)
public class FlattenedWorldManagerMixin {

    @Inject(method = "loadChunk", at = @At(
            value = "INVOKE",
            target = "Lnet/modificationstation/stationapi/impl/world/chunk/ChunkSection;getLightArray(Lnet/minecraft/world/LightType;)Lnet/modificationstation/stationapi/impl/world/chunk/NibbleArray;",
            shift = At.Shift.AFTER
    ))
    private static void fixLoadedChunk(World world, NbtCompound chunkTag, CallbackInfoReturnable<Chunk> cir, @Local(name = "chunkSection") ChunkSection chunkSection) {
        if (NotCopperTools.CONFIG.convertBlocks) NCTDatafixer.fixChunk(chunkSection);
    }

}
