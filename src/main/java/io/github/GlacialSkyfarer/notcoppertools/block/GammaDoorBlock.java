package io.github.GlacialSkyfarer.notcoppertools.block;

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

public class GammaDoorBlock extends TemplateBlock {

    public static final BooleanProperty TOP = BooleanProperty.of("top");
    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public static final BooleanProperty FLIPPED = BooleanProperty.of("flipped");
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public GammaDoorBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(TOP);
        builder.add(FACING);
        builder.add(FLIPPED);
        builder.add(OPEN);
        super.appendProperties(builder);

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
        return getDefaultState().with(TOP, false).with(FACING, context.getHorizontalPlayerFacing().getOpposite()).with(FLIPPED, isFlipped(context)).with(OPEN, false);
    }

    @Override
    public int getPistonBehavior() {
        return 1;
    }

    private boolean isFlipped(ItemPlacementContext context) {

        Direction playerDirection = context.getHorizontalPlayerFacing();
        Direction toRight = playerDirection.rotateYClockwise();
        BlockPos rightBlock = context.getBlockPos().add(toRight.getVector());
        int rightId = context.getWorld().getBlockId(rightBlock.x, rightBlock.y, rightBlock.z);
        if (rightId == id) return false;
        BlockPos leftBlock = context.getBlockPos().add(toRight.getOpposite().getVector());
        int leftId = context.getWorld().getBlockId(leftBlock.x, leftBlock.y, leftBlock.z);
        return !(leftId != 0 && leftId != id);

    }


    protected void updateBoundingBox(World world, int x, int y, int z) {

        if (world.getBlockId(x,y,z)!=id) { setBoundingBox(0, 0, 0, 1, 2, 1); return; }
        BlockState state = world.getBlockState(x,y,z);
        Direction d = state.get(FACING).getOpposite();
        if (state.get(OPEN)) {
            if (state.get(FLIPPED)) d = d.rotateYCounterclockwise();
            else d = d.rotateYClockwise();
        }

        switch (d) {
            case EAST -> setBoundingBox(0, 0, 0.8125f, 1, 1, 1);
            case WEST -> setBoundingBox(0, 0, 0, 1, 1, 0.1875f);
            case NORTH -> setBoundingBox(0.8125f, 0, 0, 1, 1, 1);
            case SOUTH -> setBoundingBox(0, 0, 0, 0.1875f, 1, 1);
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
    public boolean canPlaceAt(World world, int x, int y, int z) {
        if (y >= 255) {
            return false;
        } else {
            return world.shouldSuffocate(x, y - 1, z) && super.canPlaceAt(world, x, y, z) && super.canPlaceAt(world, x, y + 1, z);
        }
    }

    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        super.onPlaced(world, x, y, z);
        BlockState state = world.getBlockState(x,y,z);
        world.setBlock(x,y+1,z,id);
        world.setBlockStateWithNotify(x,y+1,z,state.with(TOP, true));
        updateBoundingBox(world,x,y,z);
        updateBoundingBox(world,x,y+1,z);
    }

    @Override
    public void onBlockBreakStart(World world, int x, int y, int z, PlayerEntity player) {
        this.onUse(world,x,y,z,player);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {

        if (world.getBlockState(x,y,z).get(TOP)) {

            if (world.getBlockId(x,y-1,z) != this.id) {
                world.setBlock(x,y,z,0);
            }
            if (id > 0 && Block.BLOCKS[id].canEmitRedstonePower()) {
                this.neighborUpdate(world, x,y-1,z,id);
            }

        } else {

            boolean shouldBreak = false;

            if (world.getBlockId(x, y + 1, z) != this.id) {
                world.setBlock(x, y, z, 0);
                shouldBreak = true;
            }

            if (!world.shouldSuffocate(x, y - 1, z)) {
                world.setBlock(x, y, z, 0);
                shouldBreak = true;
                if (world.getBlockId(x, y + 1, z) == this.id) {
                    world.setBlock(x, y + 1, z, 0);
                }
            }

            if (!shouldBreak) {
                if (id > 0 && Block.BLOCKS[id].canEmitRedstonePower()) {
                    boolean isPowered = world.isEmittingRedstonePower(x, y, z) || world.isEmittingRedstonePower(x, y + 1, z);
                    this.setOpen(world, x, y, z, isPowered);
                }
            }

        }

    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockState currentState = world.getBlockState(x,y,z);
        boolean isOpen = !currentState.get(OPEN);
        setOpen(world, x,y,z, isOpen);
        return true;
    }
    private void setOpen(World world, int x, int y, int z, boolean value) {

        BlockState currentState = world.getBlockState(x,y,z);
        if(currentState.get(TOP)) {
            if (world.getBlockId(x, y - 1, z) == this.id) {
                this.setOpen(world, x, y - 1, z, value);
            }
            return;
        }
        if (value == currentState.get(OPEN)) return;
        world.setBlockStateWithNotify(x,y,z, currentState.with(OPEN, value));
        if (world.getBlockId(x,y+1,z) == this.id) world.setBlockState(x,y+1,z, world.getBlockState(x,y+1,z).with(OPEN, value));
        world.setBlocksDirty(x, y - 1, z, x, y, z);
        world.worldEvent(null, 1003, x, y, z, 0);

    }

}
