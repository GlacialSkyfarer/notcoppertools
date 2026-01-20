package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.container.AnvilScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.DirectionProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

public class AnvilBlock extends TemplateBlockWithEntity {

    public AnvilBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
        super.appendProperties(builder);

    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new AnvilBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.getBlockEntity(x,y,z) instanceof AnvilBlockEntity anvil) {
            GuiHelper.openGUI(player, NotCopperTools.NAMESPACE.id("anvil"), anvil, new AnvilScreenHandler(player, anvil));
            return true;
        }
        return false;
    }



//    @Override
//    public void onPlaced(World world, int x, int y, int z) {
//        fall(world,x,y,z);
//        super.onPlaced(world,x,y,z);
//    }
//
//    @Override
//    public void neighborUpdate(World world, int x, int y, int z, int id) {
//        fall(world,x,y,z);
//        super.neighborUpdate(world,x,y,z,id);
//    }
//
//    protected void fall(World world, int x, int y, int z) {
//        if (SandBlock.canFallThrough(world, x,y-1,z)) {
//            world.setBlock(x, y, z, 0);
//            FallingBlockEntity var9 = new FallingBlockEntity(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.id);
//            world.spawnEntity(var9);
//        }
//    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
