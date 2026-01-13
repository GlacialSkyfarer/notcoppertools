package io.github.GlacialSkyfarer.notcoppertools.reef_biome.block;

import io.github.GlacialSkyfarer.notcoppertools.block.GammaDoorBlock;
import io.github.GlacialSkyfarer.notcoppertools.block.GammaSlabBlock;
import io.github.GlacialSkyfarer.notcoppertools.block.GammaTrapdoorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;

import static io.github.GlacialSkyfarer.notcoppertools.NotCopperTools.NAMESPACE;

public class ReefBiomeBlocks {

    public static Block ReefwoodLog;
    public static Block ReefwoodPlanks;
    public static Block ReefwoodDoor;
    public static Block ReefwoodTrapdoor;
    public static Block ReefwoodSlab;

    public static void registerBlocks(BlockRegistryEvent event) {

        ReefwoodLog = new TemplateBlock(NAMESPACE.id("reefwood_log"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("reefwood_log"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        ReefwoodPlanks = new TemplateBlock(NAMESPACE.id("reefwood_planks"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("reefwood_planks"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(5.0f);
        ReefwoodDoor = new GammaDoorBlock(NAMESPACE.id("reefwood_door"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("reefwood_door"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f)
                .disableTrackingStatistics();
        ReefwoodTrapdoor = new GammaTrapdoorBlock(NAMESPACE.id("reefwood_trapdoor"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("reefwood_trapdoor"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f)
                .disableTrackingStatistics();
        ReefwoodSlab = new GammaSlabBlock(NAMESPACE.id("reefwood_slab"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("reefwood_slab"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);

    }
}
