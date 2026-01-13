package io.github.GlacialSkyfarer.notcoppertools.mixin.worldgen;

import io.github.GlacialSkyfarer.notcoppertools.feature.Features;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(OverworldChunkGenerator.class)
public abstract class OverworldChunkGeneratorMixin {

    @Shadow
    private Random random;
    @Shadow
    private World world;

    @Unique
    private static final int COPPER_ATTEMPTS = 20;

    @Inject(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;method_1781()Lnet/minecraft/world/biome/source/BiomeSource;",
                    ordinal = 1
            )
    )
    public void insertDecorateWorldgen(ChunkSource source, int x, int z, CallbackInfo ci) {

        int chunkOriginX = x * 16;
        int chunkOriginZ = z * 16;

        for(int i = 0; i < COPPER_ATTEMPTS; ++i) {
            int xPos = chunkOriginX + this.random.nextInt(16);
            int height = this.random.nextInt(128);
            int zPos = chunkOriginZ + this.random.nextInt(16);
            (Features.COPPER_ORE).generate(this.world, this.random, xPos, height, zPos);
        }

    }

}
