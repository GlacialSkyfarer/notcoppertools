package io.github.GlacialSkyfarer.notcoppertools.datafixer;

import io.github.GlacialSkyfarer.notcoppertools.Directions;
import io.github.GlacialSkyfarer.notcoppertools.block.*;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.impl.world.chunk.ChunkSection;

public abstract class NCTDatafixer {

    static Block[] woodBlocks = new Block[] { Blocks.OAK_LOG, Blocks.CONIFER_LOG, Blocks.BIRCH_LOG };
    static Block[] leafBlocks = new Block[] { Blocks.OAK_LEAVES, Blocks.CONIFER_LEAVES, Blocks.BIRCH_LEAVES };
    static Block[] woolBlocks = new Block[] { Blocks.WHITE_WOOL, Blocks.ORANGE_WOOL, Blocks.MAGENTA_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.YELLOW_WOOL, Blocks.LIME_WOOL, Blocks.PINK_WOOL, Blocks.GRAY_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.CYAN_WOOL, Blocks.PURPLE_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.GREEN_WOOL, Blocks.RED_WOOL, Blocks.BLACK_WOOL };
    static Block[] slabBlocks = new Block[] { Blocks.STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.OAK_SLAB, Blocks.COBBLESTONE_SLAB };
    static Item[] dyeItems = new Item[] { Items.INK_SAC, Items.ROSE_RED, Items.CACTUS_GREEN, Items.COCOA_BEANS, Items.LAPIS_LAZULI, Items.PURPLE_DYE, Items.CYAN_DYE, Items.LIGHT_GRAY_DYE, Items.GRAY_DYE, Items.PINK_DYE, Items.LIME_DYE, Items.DANDELION_YELLOW, Items.LIGHT_BLUE_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.BONE_MEAL };

    public static void fixChunk(ChunkSection chunk) {
        for (short i = 0; i < 4096; i++) {
            byte x = (byte) (i & 15);
            byte y = (byte) ((i >> 4) & 15);
            byte z = (byte) (i >> 8);

            BlockState state = chunk.getBlockState(x,y,z);

            if (state.getBlock() == Block.LOG) {
                chunk.setBlockState(x,y,z, woodBlocks[chunk.getMeta(x,y,z)].getDefaultState());
            }
            if (state.getBlock() == Block.FENCE) {
                chunk.setBlockState(x,y,z, Blocks.OAK_FENCE.getDefaultState()
                        .with(NCTFenceBlock.NORTH, false)
                        .with(NCTFenceBlock.SOUTH, false)
                        .with(NCTFenceBlock.EAST, false)
                        .with(NCTFenceBlock.WEST, false));
            }
            if (state.getBlock() == Block.LEAVES) {
                chunk.setBlockState(x,y,z, leafBlocks[chunk.getMeta(x,y,z)].getDefaultState().with(NCTLeavesBlock.DISTANCE, 4));
            }
            if (state.getBlock() == Block.WOOL) {
                chunk.setBlockState(x,y,z,woolBlocks[chunk.getMeta(x,y,z)].getDefaultState());
            }
            if (state.getBlock() == Block.SLAB) {
                chunk.setBlockState(x,y,z, slabBlocks[chunk.getMeta(x,y,z)].getDefaultState().with(NCTSlabBlock.TYPE, SlabType.BOTTOM));
            }
            if (state.getBlock() == Block.DOUBLE_SLAB) {
                chunk.setBlockState(x,y,z, slabBlocks[chunk.getMeta(x,y,z)].getDefaultState().with(NCTSlabBlock.TYPE, SlabType.DOUBLE));
            }
            if (state.getBlock() == Block.WOODEN_STAIRS) {
                Direction[] metaToDirection = new Direction[] { Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST };
                chunk.setBlockState(x,y,z, Blocks.OAK_STAIRS.getDefaultState().with(NCTStairsBlock.TOP, false).with(NCTStairsBlock.FACING, Directions.toModern(metaToDirection[chunk.getMeta(x,y,z)])));
            }
            if (state.getBlock() == Block.COBBLESTONE_STAIRS) {
                Direction[] metaToDirection = new Direction[] { Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST };
                chunk.setBlockState(x,y,z, Blocks.COBBLESTONE_STAIRS.getDefaultState().with(NCTStairsBlock.TOP, false).with(NCTStairsBlock.FACING, Directions.toModern(metaToDirection[chunk.getMeta(x,y,z)])));
            }
        }
    }
    public static int fixItemStack(ItemStack stack) {
        int id = stack.itemId;
        int damage = stack.getDamage();

        if (id == Item.WOODEN_DOOR.id) {
            return Blocks.OAK_DOOR.asItem().id;
        }
        if (id == Block.LOG.asItem().id) {
            return woodBlocks[damage].asItem().id;
        }
        if (id == Block.LEAVES.asItem().id) {
            return leafBlocks[damage].asItem().id;
        }
        if (id == Block.WOOL.asItem().id) {
            return woolBlocks[damage].asItem().id;
        }
        if (id == Block.SLAB.asItem().id) {
            return slabBlocks[damage].asItem().id;
        }
        if (id == Block.WOODEN_STAIRS.asItem().id) {
            return Blocks.OAK_STAIRS.asItem().id;
        }
        if (id == Block.COBBLESTONE_STAIRS.asItem().id) {
            return Blocks.COBBLESTONE_STAIRS.asItem().id;
        }
        if (id == Item.DYE.id) {
            return dyeItems[damage].id;
        }

        return id;
    }

}
