package io.github.GlacialSkyfarer.gamma173.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.registry.tag.BlockTags;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.MutableBlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

public class GammaLeavesBlock extends TemplateBlock {

    public GammaLeavesBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setDefaultState(getStateManager().getDefaultState().with(PERSISTENT, false).with(DISTANCE, 7));
        setTickRandomly(true);
    }

    @Environment(EnvType.CLIENT)
    public static int getBlockColor(BlockState blockState, BlockView blockView, BlockPos blockPos, int i) {
        if (!(blockState.getBlock() instanceof GammaLeavesBlock block)) return -1;
        if (block.colorType == ColorType.SPRUCE) {
            return FoliageColors.getSpruceColor();
        } else if (block.colorType == ColorType.BIRCH) {
            return FoliageColors.getBirchColor();
        } else if (block.colorType == ColorType.OAK) {
            blockView.method_1781().getBiomesInArea(blockPos.x, blockPos.z, 1, 1);
            double temperature = blockView.method_1781().temperatureMap[0];
            double humidity = blockView.method_1781().downfallMap[0];
            return FoliageColors.getColor(temperature, humidity);
        } else {
            return -1;
        }
    }
    @Environment(EnvType.CLIENT)
    public static int getItemColor(ItemStack stack, int index) {

        if (!(stack.getItem() instanceof BlockItem blockItem) || !(blockItem.getBlock() instanceof GammaLeavesBlock leaves)) return -1;
        return switch (leaves.colorType) {
            case NONE -> -1;
            case OAK -> FoliageColors.getDefaultColor();
            case BIRCH -> FoliageColors.getBirchColor();
            case SPRUCE -> FoliageColors.getSpruceColor();
        };

    }

    @Override
    public int getColor(int meta) {
        return switch (this.colorType) {
            case NONE -> -1;
            case OAK -> FoliageColors.getDefaultColor();
            case BIRCH -> FoliageColors.getBirchColor();
            case SPRUCE -> FoliageColors.getSpruceColor();
        };
    }

    @Override
    public int getColorMultiplier(BlockView blockView, int x, int y, int z) {
        if (!(BLOCKS[blockView.getBlockId(x,y,z)] instanceof GammaLeavesBlock block)) return -1;
        if (block.colorType == ColorType.SPRUCE) {
            return FoliageColors.getSpruceColor();
        } else if (block.colorType == ColorType.BIRCH) {
            return FoliageColors.getBirchColor();
        } else if (block.colorType == ColorType.OAK) {
            blockView.method_1781().getBiomesInArea(x, z, 1, 1);
            double temperature = blockView.method_1781().temperatureMap[0];
            double humidity = blockView.method_1781().downfallMap[0];
            return FoliageColors.getColor(temperature, humidity);
        } else {
            return -1;
        }
    }

    public enum ColorType {
        NONE,
        OAK,
        BIRCH,
        SPRUCE
    }

    public static final IntProperty DISTANCE = IntProperty.of("distance", 1, 7);
    public static final BooleanProperty PERSISTENT = BooleanProperty.of("persistent");

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(DISTANCE);
        builder.add(PERSISTENT);
        super.appendProperties(builder);

    }

    protected ColorType colorType = ColorType.NONE;
    protected Item rareDrop = null;
    protected Item sapling = null;

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        super.onPlaced(world, x, y, z);
        updateDistance(world.getBlockState(x,y,z), world, new BlockPos(x,y,z));
    }

    @Override
    public boolean isFullCube() {
        return !Minecraft.isFancyGraphicsEnabled();
    }

    @Override
    public boolean isOpaque() {
        return !Minecraft.isFancyGraphicsEnabled();
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        super.onTick(world,x,y,z,random);
        updateDistance(world.getBlockState(x,y,z), world, new BlockPos(x,y,z));
        if (decaying(world.getBlockState(x,y,z))) {
            world.setBlock(x,y,z,0);
            this.dropStacks(world, x,y,z, 0);
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        super.neighborUpdate(world,x,y,z,id);
        BlockState state = world.getBlockState(x,y,z);
        if (!(state.getBlock() instanceof GammaLeavesBlock)) return;
        if (state.get(PERSISTENT)) return;

        updateDistance(state, world, new BlockPos(x,y,z));
    }

    @Override
    public void afterBreak(World world, PlayerEntity playerEntity, int x, int y, int z, int meta) {
        if (!world.isRemote && playerEntity.getHand() != null && playerEntity.getHand().getItem() instanceof ShearsItem) {
            this.dropStack(world, x, y, z, new ItemStack(this.asItem(), 1));
        } else {
            super.afterBreak(world, playerEntity, x, y, z, meta);
        }
    }

    @Override
    public List<ItemStack> getDropList(World world, int x, int y, int z, BlockState state, int meta) {
        Random r = world.random;
        List<ItemStack> drops = new ArrayList<>();

        if (sapling != null && r.nextInt(0, 10) == 0) drops.add(new ItemStack(sapling, 1));
        if (rareDrop != null && r.nextInt(0, 15) == 0) drops.add(new ItemStack(rareDrop, 1));

        return drops;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(PERSISTENT, true).with(DISTANCE, 7);
    }

    protected static boolean decaying(BlockState blockState) {
        return !blockState.get(PERSISTENT) && blockState.get(DISTANCE) == 7;
    }

    private static void updateDistance(BlockState blockState, World world, BlockPos blockPos) {
        int i = 7;
        MutableBlockPos mutableBlockPos = blockPos.mutableCopy();

        for(Direction direction : Direction.values()) {

            i = Math.min(i, getDistanceAt(world.getBlockState(mutableBlockPos.offset(direction))) + 1);
            if (i == 1) {
                break;
            }
        }

        if (i != blockState.get(DISTANCE)) {
            world.setBlockState(blockPos, blockState.with(DISTANCE, i));
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = mutableBlockPos.offset(direction);
                if (world.getBlockState(offsetPos).contains(DISTANCE)) updateDistance(world.getBlockState(offsetPos), world, offsetPos);
            }
        }
    }

    private static int getDistanceAt(BlockState blockState) {
        return getOptionalDistanceAt(blockState).orElse(7);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState blockState) {
        if (blockState.isIn(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return blockState.contains(DISTANCE) ? OptionalInt.of(blockState.get(DISTANCE)) : OptionalInt.empty();
        }
    }

    public GammaLeavesBlock setColorType(ColorType value) { this.colorType = value; return this; }
    public GammaLeavesBlock setRareDrop(Item value) { this.rareDrop = value; return this; }
    public GammaLeavesBlock setSapling(Item value) { this.sapling = value; return this; }

}
