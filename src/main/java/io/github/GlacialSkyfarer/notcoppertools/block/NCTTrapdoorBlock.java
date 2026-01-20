package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.Directions;
import io.github.GlacialSkyfarer.notcoppertools.Util;
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
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.state.property.DirectionProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class NCTTrapdoorBlock extends TemplateBlock {

    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public static final BooleanProperty TOP = BooleanProperty.of("top");
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public NCTTrapdoorBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
        builder.add(TOP);
        builder.add(OPEN);
        super.appendProperties(builder);

    }

    protected boolean ignoreHand = false;
    protected boolean ignoreRedstone = false;

    public NCTTrapdoorBlock setIgnoreHand() {
        this.ignoreHand = true;
        return this;
    }
    public NCTTrapdoorBlock setIgnoreRedstone() {
        this.ignoreRedstone = true;
        return this;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, getPlacementDirection(context, context.getHorizontalPlayerFacing().getOpposite())).with(TOP, isTop(context)).with(OPEN, false);
    }

    private Direction getPlacementDirection(ItemPlacementContext context, Direction desiredDirection) {

        Direction result = desiredDirection;

        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        if (!world.shouldSuffocate(blockPos.x,blockPos.y-1,blockPos.z)) {

            for (int i = 0; i < 4; i++) {

                BlockPos pos = blockPos.add(result.getOpposite().getVector());

                if (world.shouldSuffocate(pos.x,pos.y,pos.z)) {

                    return Directions.toModern(result);

                }
                result = result.rotateYClockwise();

            }

        }
        return Directions.toModern(result);
    }

    private boolean isTop(ItemPlacementContext context) {

        BlockPos pos = context.getBlockPos();
        if (!hasNeighbor(context.getWorld(), pos.x, pos.y, pos.z)) return false;
        assert context.getPlayer() != null;
        return Util.isPlayerLookingAtTopHalfOfBlock(context.getPlayer(), pos.y);

    }

    protected void updateBoundingBox(World world, int x, int y, int z) {

        if (world.getBlockId(x,y,z)!=id) { setBoundingBox(0, 0, 0, 1, 1, 1); return; }
        BlockState state = world.getBlockState(x,y,z);

        if (!state.get(OPEN)) {

            if (state.get(TOP)) {

                setBoundingBox(0,0.8125f,0,1,1,1);

            } else {

                setBoundingBox(0,0,0,1,0.1875f,1);

            }

            return;

        }

        Direction d = state.get(FACING);

        switch (d) {
            case WEST -> setBoundingBox(0, 0, 0, 0.1875f, 1, 1);
            case EAST -> setBoundingBox(0.8125f, 0, 0, 1, 1, 1);
            case SOUTH -> setBoundingBox(0, 0, 0.8125f, 1, 1, 1);
            case NORTH -> setBoundingBox(0, 0, 0, 1, 1, 0.1875f);
            default -> setBoundingBox(0, 0, 0, 1, 1, 1);
        }

    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        updateBoundingBox(world,x,y,z);
        return super.getBoundingBox(world, x, y, z);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        updateBoundingBox(world,x,y,z);
        return super.getCollisionShape(world, x, y, z);
    }

    @Override
    public HitResult raycast(World world, int x, int y, int z, Vec3d startPos, Vec3d endPos) {
        updateBoundingBox(world,x,y,z);
        return super.raycast(world, x, y, z, startPos, endPos);
    }

    @Override
    public int getPistonBehavior() {
        return 1;
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z) {
        if (y >= 256) {
            return false;
        } else {
            return (world.shouldSuffocate(x,y-1,z) || hasNeighbor(world,x,y,z)) && super.canPlaceAt(world, x, y, z);
        }
    }

    private boolean hasNeighbor(World world, int x, int y, int z) {

        return (world.shouldSuffocate(x-1,y,z)
        || world.shouldSuffocate(x+1,y,z)
        || world.shouldSuffocate(x,y,z-1)
        || world.shouldSuffocate(x,y,z+1));

    }

    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        super.onPlaced(world, x, y, z);
        updateBoundingBox(world,x,y,z);
    }

    @Override
    public void onBlockBreakStart(World world, int x, int y, int z, PlayerEntity player) {
        this.onUse(world,x,y,z,player);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {

        boolean shouldBreak = false;

        if (world.getBlockState(x,y,z).get(TOP)) {
            BlockPos anchorPoint = new BlockPos(x,y,z).add(Directions.fromModern(world.getBlockState(x,y,z).get(FACING)).getOpposite().getVector());
            if (!world.shouldSuffocate(anchorPoint.x,anchorPoint.y,anchorPoint.z)) {
                shouldBreak = true;
                world.setBlock(x,y,z,0);
            }
        } else {

            if (!world.shouldSuffocate(x,y-1,z)) {

                BlockPos anchorPoint = new BlockPos(x,y,z).add(Directions.fromModern(world.getBlockState(x,y,z).get(FACING)).getOpposite().getVector());
                if (!world.shouldSuffocate(anchorPoint.x,anchorPoint.y,anchorPoint.z)) {
                    shouldBreak = true;
                    world.setBlock(x,y,z,0);
                }

            }

        }

        if (shouldBreak) {
            if (!world.isRemote) {
                this.dropStacks(world,x,y,z,1);
            }
        } else
        {
            if (!ignoreRedstone && id > 0 && Block.BLOCKS[id].canEmitRedstonePower()) {
                boolean isPowered = world.isEmittingRedstonePower(x, y, z);
                this.setOpen(world, x, y, z, isPowered);
            }
        }

    }

    @Override
    public boolean canEmitRedstonePower() {
        return false;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (ignoreHand) return false;
        BlockState currentState = world.getBlockState(x,y,z);
        boolean isOpen = !currentState.get(OPEN);
        setOpen(world, x,y,z, isOpen);
        return true;
    }
    private void setOpen(World world, int x, int y, int z, boolean value) {

        BlockState currentState = world.getBlockState(x,y,z);
        if (value == currentState.get(OPEN)) return;
        world.setBlockStateWithNotify(x,y,z, currentState.with(OPEN, value));
        world.setBlockDirty(x, y, z);
        world.worldEvent(null, 1003, x, y, z, 0);

    }

}
