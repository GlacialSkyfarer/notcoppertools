package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.Directions;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.tag.NCTBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.state.property.DirectionProperty;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.tag.TagManagerLoader;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.ArrayList;

public class NCTFenceBlock extends TemplateBlock {

    protected TagKey<Block> connectTag;
    protected float width;
    protected float height;

    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");

    public NCTFenceBlock(Identifier identifier, Material material, TagKey<Block> connectTag, float width, float height) {
        super(identifier, material);
        this.connectTag = connectTag;
        this.width = width;
        this.height = height;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH).add(SOUTH).add(EAST).add(WEST);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState();
    }

    private BlockState updateState(BlockState state, World world, BlockPos pos) {
        state = state.with(NORTH, world.getBlockState(pos.offset(Directions.NORTH)).isIn(connectTag));
        state = state.with(SOUTH, world.getBlockState(pos.offset(Directions.SOUTH)).isIn(connectTag));
        state = state.with(EAST, world.getBlockState(pos.offset(Directions.EAST)).isIn(connectTag));
        state = state.with(WEST, world.getBlockState(pos.offset(Directions.WEST)).isIn(connectTag));
        return state;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void addIntersectingBoundingBox(World world, int x, int y, int z, Box box, ArrayList boxes) {
        BlockState state = world.getBlockState(x,y,z);
        if (!(state.isOf(this))) return;
        float beforeDistance = (1f - width) / 2f;
        float afterDistance = beforeDistance + width;
        setBoundingBox(beforeDistance, 0f, beforeDistance, afterDistance, height, afterDistance);
        super.addIntersectingBoundingBox(world,x,y,z,box,boxes);
        if (state.get(NORTH)) {
            setBoundingBox(beforeDistance, 0f, 0f, afterDistance, height, beforeDistance);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        }
        if (state.get(SOUTH)) {
            setBoundingBox(beforeDistance, 0f, afterDistance, afterDistance, height, 1f);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        }
        if (state.get(WEST)) {
            setBoundingBox(0.0f, 0f, beforeDistance, beforeDistance, height, afterDistance);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        }
        if (state.get(EAST)) {
            setBoundingBox(afterDistance, 0f, beforeDistance, 1f, height, afterDistance);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        }
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    protected void updateBoundingBox(World world, int x, int y, int z) {
        if (world.getBlockId(x,y,z)!=id) { setBoundingBox(0, 0, 0, 1, 1, 1); return; }
        BlockState state = world.getBlockState(x,y,z);

        float beforeDistance = (1f - width) / 2f;
        float afterDistance = beforeDistance + width;

        float   miX = beforeDistance,
                miZ = beforeDistance,
                maX = afterDistance,
                maZ = afterDistance;

        if (state.get(NORTH)) miZ = 0f;
        if (state.get(SOUTH)) maZ = 1f;
        if (state.get(WEST))  miX = 0f;
        if (state.get(EAST))  maX = 1f;

        setBoundingBox(miX, 0f, miZ, maX, 1f, maZ);
    }

    @Override
    public HitResult raycast(World world, int x, int y, int z, Vec3d startPos, Vec3d endPos) {
        updateBoundingBox(world,x,y,z);
        return super.raycast(world, x, y, z, startPos, endPos);
    }

    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        super.onPlaced(world, x, y, z);
        world.setBlockState(x,y,z, updateState(world.getBlockState(x,y,z), world, new BlockPos(x,y,z)));
        updateBoundingBox(world,x,y,z);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        world.setBlockState(x,y,z, updateState(world.getBlockState(x,y,z), world, new BlockPos(x,y,z)));
    }

}
