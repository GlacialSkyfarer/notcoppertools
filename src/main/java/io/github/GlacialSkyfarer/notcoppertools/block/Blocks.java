package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import io.github.GlacialSkyfarer.notcoppertools.tag.NCTBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.*;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;

import static io.github.GlacialSkyfarer.notcoppertools.NotCopperTools.NAMESPACE;

public abstract class Blocks {

    //Vanilla replacements
    public static Block
    OAK_LOG,
    CONIFER_LOG,
    BIRCH_LOG,

    CONIFER_PLANKS,
    BIRCH_PLANKS,

    OAK_SAPLING,
    CONIFER_SAPLING,
    BIRCH_SAPLING,

    OAK_LEAVES,
    CONIFER_LEAVES,
    BIRCH_LEAVES,

    OAK_SLAB,
    CONIFER_SLAB,
    BIRCH_SLAB,

    OAK_STAIRS,
    CONIFER_STAIRS,
    BIRCH_STAIRS,

    OAK_DOOR,

    OAK_TRAPDOOR,
    CONIFER_TRAPDOOR,
    BIRCH_TRAPDOOR,

    OAK_FENCE,
    CONIFER_FENCE,
    BIRCH_FENCE,


    COBBLESTONE_SLAB,
    COBBLESTONE_STAIRS,

    STONE_SLAB,
    STONE_STAIRS,
    STONE_BRICKS,
    CHISELED_STONE,
    CARVED_STONE,
    STONE_BRICK_SLAB,
    STONE_BRICK_STAIRS,

    SANDSTONE_SLAB,
    SANDSTONE_STAIRS,
    SANDSTONE_BRICKS,
    CHISELED_SANDSTONE,
    CARVED_SANDSTONE,
    SANDSTONE_BRICK_SLAB,
    SANDSTONE_BRICK_STAIRS,

    NETHERRACK_SLAB,
    NETHERRACK_STAIRS,
    NETHERRACK_BRICKS,
    CHISELED_NETHERRACK,
    CARVED_NETHERRACK,
    NETHERRACK_BRICK_SLAB,
    NETHERRACK_BRICK_STAIRS,

    BRICK_SLAB,
    BRICK_STAIRS,


    COPPER_ORE,
    COPPER_BLOCK,
    COPPER_TILES,
    COPPER_TILE_STAIRS,
    COPPER_TILE_SLAB,

    COAL_BLOCK,
    REDSTONE_BLOCK,


    ANVIL,
    STONECUTTER,

    WHITE_WOOL, BLACK_WOOL, GRAY_WOOL, LIGHT_GRAY_WOOL, BROWN_WOOL,
    RED_WOOL, ORANGE_WOOL, YELLOW_WOOL, LIME_WOOL, GREEN_WOOL,
    CYAN_WOOL, LIGHT_BLUE_WOOL, BLUE_WOOL, PURPLE_WOOL, MAGENTA_WOOL, PINK_WOOL,

    IRON_GRATING,
    COPPER_GRATING,
    GOLD_GRATING;

    public static void registerBlocks(BlockRegistryEvent event) {

        //region wood
        OAK_LOG = new TemplateBlock(NAMESPACE.id("oak_log"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("oak_log"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        CONIFER_LOG = new TemplateBlock(NAMESPACE.id("conifer_log"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("conifer_log"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        BIRCH_LOG = new TemplateBlock(NAMESPACE.id("birch_log"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("birch_log"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);


        CONIFER_PLANKS = new TemplateBlock(NAMESPACE.id("conifer_planks"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("conifer_planks"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(5.0f);
        BIRCH_PLANKS = new TemplateBlock(NAMESPACE.id("birch_planks"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("birch_planks"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(5.0f);


        OAK_SAPLING = new NCTSaplingBlock(NAMESPACE.id("oak_sapling"))
                .addTree(new OakTreeFeature(), 10)
                .addTree(new LargeOakTreeFeature(), 1)
                .setTranslationKey(NAMESPACE.id("oak_sapling"))
                .setSoundGroup(Block.SAPLING.soundGroup)
                .setHardness(0.0f);
        CONIFER_SAPLING = new NCTSaplingBlock(NAMESPACE.id("conifer_sapling"))
                .addTree(new PineTreeFeature(), 1)
                .addTree(new SpruceTreeFeature(), 1)
                .setTranslationKey(NAMESPACE.id("conifer_sapling"))
                .setSoundGroup(Block.SAPLING.soundGroup)
                .setHardness(0.0f);
        BIRCH_SAPLING = new NCTSaplingBlock(NAMESPACE.id("birch_sapling"))
                .addTree(new BirchTreeFeature(), 1)
                .setTranslationKey(NAMESPACE.id("birch_sapling"))
                .setSoundGroup(Block.SAPLING.soundGroup)
                .setHardness(0.0f);


        OAK_LEAVES = new NCTLeavesBlock(NAMESPACE.id("oak_leaves"), Material.LEAVES)
                .setColorType(NCTLeavesBlock.ColorType.OAK)
                .setRareDrop(Item.APPLE)
                .setTranslationKey(NAMESPACE.id("oak_leaves"))
                .setSoundGroup(Block.LEAVES.soundGroup)
                .setHardness(0.2f);
        CONIFER_LEAVES = new NCTLeavesBlock(NAMESPACE.id("conifer_leaves"), Material.LEAVES)
                .setColorType(NCTLeavesBlock.ColorType.SPRUCE)
                .setRareDrop(Items.SPRUCE_RESIN)
                .setTranslationKey(NAMESPACE.id("conifer_leaves"))
                .setSoundGroup(Block.LEAVES.soundGroup)
                .setHardness(0.2f);
        BIRCH_LEAVES = new NCTLeavesBlock(NAMESPACE.id("birch_leaves"), Material.LEAVES)
                .setColorType(NCTLeavesBlock.ColorType.BIRCH)
                .setRareDrop(Items.BAGWORM_SILK)
                .setTranslationKey(NAMESPACE.id("birch_leaves"))
                .setSoundGroup(Block.LEAVES.soundGroup)
                .setHardness(0.2f);


        OAK_SLAB = new NCTSlabBlock(NAMESPACE.id("oak_slab"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("oak_slab"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        CONIFER_SLAB = new NCTSlabBlock(NAMESPACE.id("conifer_slab"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("conifer_slab"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        BIRCH_SLAB = new NCTSlabBlock(NAMESPACE.id("birch_slab"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("birch_slab"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);


        OAK_STAIRS = new NCTStairsBlock(NAMESPACE.id("oak_stairs"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("oak_stairs"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        CONIFER_STAIRS = new NCTStairsBlock(NAMESPACE.id("conifer_stairs"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("conifer_stairs"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        BIRCH_STAIRS = new NCTStairsBlock(NAMESPACE.id("birch_stairs"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("birch_stairs"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);


        OAK_DOOR = new NCTDoorBlock(NAMESPACE.id("oak_door"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("oak_door"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f)
                .disableTrackingStatistics();


        OAK_TRAPDOOR = new NCTTrapdoorBlock(NAMESPACE.id("oak_trapdoor"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("oak_trapdoor"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f);
        CONIFER_TRAPDOOR = new NCTTrapdoorBlock(NAMESPACE.id("conifer_trapdoor"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("conifer_trapdoor"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f);
        BIRCH_TRAPDOOR = new NCTTrapdoorBlock(NAMESPACE.id("birch_trapdoor"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("birch_trapdoor"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(3.0f);


        OAK_FENCE = new NCTFenceBlock(NAMESPACE.id("oak_fence"), Material.WOOD, NCTBlockTags.FENCE_CONNECTED, 0.25f, 1.5f)
                .setTranslationKey(NAMESPACE.id("oak_fence"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        CONIFER_FENCE = new NCTFenceBlock(NAMESPACE.id("conifer_fence"), Material.WOOD, NCTBlockTags.FENCE_CONNECTED, 0.25f, 1.5f)
                .setTranslationKey(NAMESPACE.id("conifer_fence"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        BIRCH_FENCE = new NCTFenceBlock(NAMESPACE.id("birch_fence"), Material.WOOD, NCTBlockTags.FENCE_CONNECTED, 0.25f, 1.5f)
                .setTranslationKey(NAMESPACE.id("birch_fence"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0f);
        //endregion
        //region stones
        COBBLESTONE_SLAB = new NCTSlabBlock(NAMESPACE.id("cobblestone_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("cobblestone_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f);
        COBBLESTONE_STAIRS = new NCTStairsBlock(NAMESPACE.id("cobblestone_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("cobblestone_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f);


        STONE_SLAB = new NCTSlabBlock(NAMESPACE.id("stone_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stone_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(1.5f);
        STONE_STAIRS = new NCTStairsBlock(NAMESPACE.id("stone_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stone_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(1.5f);
        STONE_BRICKS = new TemplateBlock(NAMESPACE.id("stone_bricks"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stone_bricks"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.4f)
                .setResistance(10.0f);
        CHISELED_STONE = new TemplateBlock(NAMESPACE.id("chiseled_stone"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("chiseled_stone"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(10.0f);
        CARVED_STONE = new TemplateBlock(NAMESPACE.id("carved_stone"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("carved_stone"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(10.0f);
        STONE_BRICK_SLAB = new NCTSlabBlock(NAMESPACE.id("stone_brick_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stone_brick_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.4f)
                .setResistance(10.0f);
        STONE_BRICK_STAIRS = new NCTStairsBlock(NAMESPACE.id("stone_brick_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stone_brick_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.4f)
                .setResistance(10.0f);


        SANDSTONE_SLAB = new NCTSlabBlock(NAMESPACE.id("sandstone_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("sandstone_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        SANDSTONE_STAIRS = new NCTStairsBlock(NAMESPACE.id("sandstone_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("sandstone_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        SANDSTONE_BRICKS = new TemplateBlock(NAMESPACE.id("sandstone_bricks"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("sandstone_bricks"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(1.2f);
        CARVED_SANDSTONE = new TemplateBlock(NAMESPACE.id("carved_sandstone"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("carved_sandstone"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        CHISELED_SANDSTONE = new TemplateBlock(NAMESPACE.id("chiseled_sandstone"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("chiseled_sandstone"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        SANDSTONE_BRICK_SLAB = new NCTSlabBlock(NAMESPACE.id("sandstone_brick_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("sandstone_brick_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(1.2f);
        SANDSTONE_BRICK_STAIRS = new NCTStairsBlock(NAMESPACE.id("sandstone_brick_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("sandstone_brick_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(1.2f);


        NETHERRACK_SLAB = new NCTSlabBlock(NAMESPACE.id("netherrack_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("netherrack_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        NETHERRACK_STAIRS = new NCTStairsBlock(NAMESPACE.id("netherrack_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("netherrack_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        NETHERRACK_BRICKS = new TemplateBlock(NAMESPACE.id("netherrack_bricks"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("netherrack_bricks"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        CARVED_NETHERRACK = new TemplateBlock(NAMESPACE.id("carved_netherrack"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("carved_netherrack"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        CHISELED_NETHERRACK = new TemplateBlock(NAMESPACE.id("chiseled_netherrack"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("chiseled_netherrack"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        NETHERRACK_BRICK_SLAB = new NCTSlabBlock(NAMESPACE.id("netherrack_brick_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("netherrack_brick_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);
        NETHERRACK_BRICK_STAIRS = new NCTStairsBlock(NAMESPACE.id("netherrack_brick_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("netherrack_brick_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(0.8f);


        BRICK_SLAB = new NCTSlabBlock(NAMESPACE.id("brick_slab"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("brick_slab"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(10.0f);
        BRICK_STAIRS = new NCTStairsBlock(NAMESPACE.id("brick_stairs"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("brick_stairs"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.0f)
                .setResistance(10.0f);


        //endregion
        //region ores
        COPPER_BLOCK = new TemplateBlock(NAMESPACE.id("copper_block"), Material.METAL)
                .setTranslationKey(NAMESPACE.id("copper_block"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(10.0f);
        COPPER_TILES = new TemplateBlock(NAMESPACE.id("copper_tiles"), Material.METAL)
                .setTranslationKey(NAMESPACE.id("copper_tiles"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(7.0f);
        COPPER_TILE_SLAB = new NCTSlabBlock(NAMESPACE.id("copper_tile_slab"), Material.METAL)
                .setTranslationKey(NAMESPACE.id("copper_tile_slab"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(7.0f);
        COPPER_TILE_STAIRS = new NCTStairsBlock(NAMESPACE.id("copper_tile_stairs"), Material.METAL)
                .setTranslationKey(NAMESPACE.id("copper_tile_stairs"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(7.0f);
        COPPER_ORE = new TemplateBlock(NAMESPACE.id("copper_ore"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("copper_ore"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(5.0f);


        COAL_BLOCK = new TemplateBlock(NAMESPACE.id("coal_block"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("coal_block"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(2.5f)
                .setResistance(7.0f);
        REDSTONE_BLOCK = new TemplateBlock(NAMESPACE.id("redstone_block"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("redstone_block"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(7.0f);
        //endregion
        //region other stuff
        ANVIL = new AnvilBlock(NAMESPACE.id("anvil"), Material.METAL)
                .setTranslationKey(NAMESPACE.id("anvil"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(7.0f)
                .setResistance(10.0f);
        STONECUTTER = new StonecutterBlock(NAMESPACE.id("stonecutter"), Material.STONE)
                .setTranslationKey(NAMESPACE.id("stonecutter"))
                .setSoundGroup(Block.STONE_SOUND_GROUP)
                .setHardness(5.0f)
                .setResistance(10.0f);

        //region wool
        WHITE_WOOL = new TemplateBlock(NAMESPACE.id("white_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("white_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        BLACK_WOOL = new TemplateBlock(NAMESPACE.id("black_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("black_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        GRAY_WOOL = new TemplateBlock(NAMESPACE.id("gray_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("gray_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        LIGHT_GRAY_WOOL = new TemplateBlock(NAMESPACE.id("light_gray_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("light_gray_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        BROWN_WOOL = new TemplateBlock(NAMESPACE.id("brown_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("brown_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        RED_WOOL = new TemplateBlock(NAMESPACE.id("red_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("red_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        ORANGE_WOOL = new TemplateBlock(NAMESPACE.id("orange_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("orange_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        YELLOW_WOOL = new TemplateBlock(NAMESPACE.id("yellow_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("yellow_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        LIME_WOOL = new TemplateBlock(NAMESPACE.id("lime_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("lime_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        GREEN_WOOL = new TemplateBlock(NAMESPACE.id("green_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("green_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        CYAN_WOOL = new TemplateBlock(NAMESPACE.id("cyan_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("cyan_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        LIGHT_BLUE_WOOL = new TemplateBlock(NAMESPACE.id("light_blue_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("light_blue_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        BLUE_WOOL = new TemplateBlock(NAMESPACE.id("blue_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("blue_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        PURPLE_WOOL = new TemplateBlock(NAMESPACE.id("purple_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("purple_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        MAGENTA_WOOL = new TemplateBlock(NAMESPACE.id("magenta_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("magenta_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        PINK_WOOL = new TemplateBlock(NAMESPACE.id("pink_wool"), Material.WOOL)
                .setTranslationKey(NAMESPACE.id("pink_wool"))
                .setSoundGroup(Block.WOOL_SOUND_GROUP)
                .setHardness(0.8f);
        //endregion

        IRON_GRATING = new NCTFenceBlock(NAMESPACE.id("iron_grating"), Material.METAL, NCTBlockTags.GRATING_CONNECTED, 0.125f, 1f)
                .setTranslationKey(NAMESPACE.id("iron_grating"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(12.0f);
        GOLD_GRATING = new NCTFenceBlock(NAMESPACE.id("gold_grating"), Material.METAL, NCTBlockTags.GRATING_CONNECTED, 0.125f, 1f)
                .setTranslationKey(NAMESPACE.id("gold_grating"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(8.0f);
        COPPER_GRATING = new NCTFenceBlock(NAMESPACE.id("copper_grating"), Material.METAL, NCTBlockTags.GRATING_CONNECTED, 0.125f, 1f)
                .setTranslationKey(NAMESPACE.id("copper_grating"))
                .setSoundGroup(Block.METAL_SOUND_GROUP)
                .setHardness(3.0f)
                .setResistance(10.0f);
        //endregion

    }
}
