package io.github.GlacialSkyfarer.notcoppertools.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class RedstoneBlock extends TemplateBlock {

    public RedstoneBlock(Identifier identifier, Material ignored) {
        super(identifier, Material.PISTON_BREAKABLE);
    }

    @Override
    public boolean isEmittingRedstonePowerInDirection(BlockView blockView, int x, int y, int z, int direction) {
        return true;
    }

    @Override
    public boolean canEmitRedstonePower() {
        return true;
    }

    @Override
    public boolean canTransferPowerInDirection(World world, int x, int y, int z, int direction) {
        return true;
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, int direction) {
        super.onPlaced(world, x, y, z, direction);
        world.notifyNeighbors(x, y - 1, z, this.id);
        world.notifyNeighbors(x, y + 1, z, this.id);
        world.notifyNeighbors(x - 1, y, z, this.id);
        world.notifyNeighbors(x + 1, y, z, this.id);
        world.notifyNeighbors(x, y, z - 1, this.id);
        world.notifyNeighbors(x, y, z + 1, this.id);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        super.onBreak(world, x, y, z);
        world.notifyNeighbors(x, y - 1, z, this.id);
        world.notifyNeighbors(x, y + 1, z, this.id);
        world.notifyNeighbors(x - 1, y, z, this.id);
        world.notifyNeighbors(x + 1, y, z, this.id);
        world.notifyNeighbors(x, y, z - 1, this.id);
        world.notifyNeighbors(x, y, z + 1, this.id);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        super.randomDisplayTick(world, x, y, z, random);
        for (int i = 0; i < random.nextInt(1, 24); i++) {
            double xPart = (double)((float)x + 0.5F) + (double)(random.nextFloat() - 0.5F) * 1.3;
            double yPart = (double)((float)x + 0.5F) + (double)(random.nextFloat() - 0.5F) * 1.3;
            double zPart = (double)((float)x + 0.5F) + (double)(random.nextFloat() - 0.5F) * 1.3;

            world.addParticle("reddust", xPart,yPart,zPart,0.0,0.0,0.0);
        }
    }

    @Override
    public boolean isFullCube() {
        return true;
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public int getPistonBehavior() {
        return 1;
    }
}
