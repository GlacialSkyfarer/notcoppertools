package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.Directions;
import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.sound.SoundHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
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

import java.util.ArrayList;
import java.util.List;

public class NCTStairsBlock extends TemplateBlock {

    public static final BooleanProperty TOP = BooleanProperty.of("top");
    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH);

    public NCTStairsBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        assert context.getPlayer() != null;
        return getDefaultState()
                .with(TOP, Util.isPlayerLookingAtTopHalfOfBlock(context.getPlayer(), context.getBlockPos().y))
                .with(FACING, Directions.toModern(context.getHorizontalPlayerFacing()).getOpposite());
    }

    @Override
    public void addIntersectingBoundingBox(World world, int x, int y, int z, Box box, ArrayList boxes) {
        BlockState state = world.getBlockState(x,y,z);
        if (!(state.isOf(this))) return;
        if (state.get(TOP)) {
            setBoundingBox(0f,0.5f,0f,1f,1f,1f);
            super.addIntersectingBoundingBox(world,x,y,z,box,boxes);
            switch (state.get(FACING)) {
                case SOUTH:
                    setBoundingBox(0f,0f,0.5f,1f,0.5f,1f);
                    break;
                case NORTH:
                    setBoundingBox(0f,0f,0f,1f,0.5f,0.5f);
                    break;
                case WEST:
                    setBoundingBox(0f,0f,0f,0.5f,0.5f,1f);
                    break;
                case EAST:
                    setBoundingBox(0.5f,0f,0f,1f,0.5f,1f);
                    break;
            }
            super.addIntersectingBoundingBox(world,x,y,z,box,boxes);
        } else {
            setBoundingBox(0f,0f,0f,1f,0.5f,1f);
            super.addIntersectingBoundingBox(world,x,y,z,box,boxes);
            switch (state.get(FACING)) {
                case SOUTH:
                    setBoundingBox(0f,0.5f,0.5f,1f,1f,1f);
                    break;
                case NORTH:
                    setBoundingBox(0f,0.5f,0f,1f,1f,0.5f);
                    break;
                case WEST:
                    setBoundingBox(0f,0.5f,0f,0.5f,1f,1f);
                    break;
                case EAST:
                    setBoundingBox(0.5f,0.5f,0f,1f,1f,1f);
                    break;
            }
            super.addIntersectingBoundingBox(world,x,y,z,box,boxes);
        }

        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(TOP)
                .add(FACING);
        super.appendProperties(builder);

    }

}
