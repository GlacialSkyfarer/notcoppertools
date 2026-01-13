package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends ToolItem {
    public PickaxeItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Unique
    private final static List<Block> addedEffectiveBlocks = new ArrayList<>();

    static {

        addedEffectiveBlocks.add(Block.FURNACE);
        addedEffectiveBlocks.add(Block.LIT_FURNACE);
        addedEffectiveBlocks.add(Block.LIT_REDSTONE_ORE);
        addedEffectiveBlocks.add(Block.REDSTONE_ORE);
        addedEffectiveBlocks.add(Block.PISTON);
        addedEffectiveBlocks.add(Block.PISTON_HEAD);
        addedEffectiveBlocks.add(Block.DISPENSER);
        addedEffectiveBlocks.add(Block.SPAWNER);
        addedEffectiveBlocks.add(Block.STICKY_PISTON);
        addedEffectiveBlocks.add(Block.BRICKS);
        addedEffectiveBlocks.add(Block.RAIL);
        addedEffectiveBlocks.add(Block.POWERED_RAIL);
        addedEffectiveBlocks.add(Block.DETECTOR_RAIL);
        addedEffectiveBlocks.add(Block.OBSIDIAN);

    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (addedEffectiveBlocks.contains(block)) return this.miningSpeed;
        return super.getMiningSpeedMultiplier(stack, block);
    }
}
