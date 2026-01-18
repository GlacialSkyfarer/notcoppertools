package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.block.data.SaplingTree;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NCTSaplingBlock extends TemplateBlock {

    public static IntProperty GROWTH = IntProperty.of("growth", 0, 1);

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GROWTH);
    }

    protected List<SaplingTree> trees = new ArrayList<>();

    public NCTSaplingBlock(Identifier id) {
        super(id, Material.PLANT);
        this.setTickRandomly(true);
        float size = 0.4F;
        this.setBoundingBox(0.5F - size, 0.0F, 0.5F - size, 0.5F + size, size * 3.0F, 0.5F + size);
    }

    public NCTSaplingBlock addTree(Feature feature, int weight) {
        this.trees.add(new SaplingTree(feature, weight));
        return this;
    }

    public boolean canPlaceAt(World world, int x, int y, int z) {
        return super.canPlaceAt(world, x, y, z) && this.canPlantOnTop(world.getBlockId(x, y - 1, z));
    }

    protected boolean canPlantOnTop(int id) {
        return id == Block.GRASS_BLOCK.id || id == Block.DIRT.id || id == Block.FARMLAND.id;
    }

    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world, x, y, z, id);
        this.breakIfCannotGrow(world, x, y, z);
    }

    public void onTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            super.onTick(world, x, y, z, random);
            if (world.getLightLevel(x, y + 1, z) >= 9 && random.nextInt(30) == 0) {
                BlockState state = world.getBlockState(x,y,z);
                if (state.get(GROWTH) == 0) {
                    world.setBlockState(x,y,z, state.with(GROWTH, 1));
                } else {
                    this.generate(world, x, y, z, random);
                }
            }
        }
    }

    @Override
    public boolean onBonemealUse(World world, int x, int y, int z, BlockState state) {
        if (world.getLightLevel(x, y + 1, z) >= 9) {
            this.generate(world, x, y, z, world.random);
            return true;
        }
        return false;
    }

    protected void generate(World world, int x, int y, int z, Random random) {
        BlockState failedState = world.getBlockState(x,y,z).with(GROWTH, 1);
        world.setBlockState(x,y,z, States.AIR.get());
        int[] intervals = new int[trees.size()];
        int current = 0;
        for (int i = 0; i < trees.size(); i++) {
            current += trees.get(i).weight;
            intervals[i] = current;
        }
        double selected = random.nextDouble() * current;
        for (int i = 0; i < trees.size(); i++) {
            if (intervals[i] > selected) {
                if (!(trees.get(i).feature.generate(world, random, x,y,z))) {
                    world.setBlockState(x,y,z, failedState);
                }
                break;
            }
        }
    }

    protected final void breakIfCannotGrow(World world, int x, int y, int z) {
        if (!this.canGrow(world, x, y, z)) {
            this.dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
            world.setBlock(x, y, z, 0);
        }
    }

    public boolean canGrow(World world, int x, int y, int z) {
        return (world.getBrightness(x, y, z) >= 8 || world.hasSkyLight(x, y, z)) && this.canPlantOnTop(world.getBlockId(x, y - 1, z));
    }

    public Box getCollisionShape(World world, int x, int y, int z) {
        return null;
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    public int getRenderType() {
        return 1;
    }
}
