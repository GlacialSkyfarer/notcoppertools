package io.github.GlacialSkyfarer.notcoppertools.block;

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
import net.modificationstation.stationapi.api.state.property.EnumProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class NCTSlabBlock extends TemplateBlock {

    public static final EnumProperty<SlabType> TYPE = EnumProperty.of("type", SlabType.class);

    public NCTSlabBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        SlabType type;
        assert context.getPlayer() != null;
        if (Util.isPlayerLookingAtTopHalfOfBlock(context.getPlayer(), context.getBlockPos().y)) type = SlabType.TOP;
        else type = SlabType.BOTTOM;
        return getDefaultState().with(TYPE, type);
    }

    public void updateBoundingBox(World world, int x, int y, int z) {

        if (world.getBlockId(x,y,z)!=id) { setBoundingBox(0, 0, 0, 1, 1, 1); return; }
        BlockState state = world.getBlockState(x,y,z);
        switch (state.get(TYPE)) {

            case TOP -> setBoundingBox(0,0.5f,0,1,1,1);
            case BOTTOM -> setBoundingBox(0,0,0,1,0.5f,1);
            case DOUBLE -> setBoundingBox(0,0,0,1,1,1);

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
    public List<ItemStack> getDropList(World world, int x, int y, int z, BlockState state, int meta) {
        if (state.isAir()) return null;
        if (state.get(TYPE) == SlabType.DOUBLE) {
            return  List.of(new ItemStack(asItem()), new ItemStack(asItem()));
        }
        return super.getDropList(world, x, y, z, state, meta);
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

        builder.add(TYPE);
        super.appendProperties(builder);

    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        int side = player.raycast(5.0,1).side;
        //NOT up or down
        if (side > 1) {
            return super.onUse(world,x,y,z,player);
        }
        ItemStack hand = player.getHand();
        if (hand == null || hand.itemId != this.asItem().id) return super.onUse(world,x,y,z,player);
        if (side == 1) {

            BlockState state = world.getBlockState(x,y,z);
            if (state.get(TYPE) == SlabType.BOTTOM) {

                hand.count -= 1;
                SoundHelper.playSound(soundGroup.getBreakSound(), x,y,z, 1, 1f);
                world.setBlockStateWithNotify(x, y, z, state.with(TYPE, SlabType.DOUBLE));
                return true;

            } else {

                return super.onUse(world,x,y,z,player);

            }

        }

        if (side == 0) {

            BlockState state = world.getBlockState(x,y,z);
            if (state.get(TYPE) == SlabType.TOP) {

                hand.count -= 1;
                SoundHelper.playSound(soundGroup.getBreakSound(), x,y,z, 1, 1f);
                world.setBlockStateWithNotify(x, y, z, state.with(TYPE, SlabType.DOUBLE));
                return true;

            } else {

                return super.onUse(world,x,y,z,player);

            }

        }

        return super.onUse(world,x,y,z,player);
    }

}
