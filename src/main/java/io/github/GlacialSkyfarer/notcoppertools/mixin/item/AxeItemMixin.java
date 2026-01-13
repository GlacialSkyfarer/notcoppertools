package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(AxeItem.class)
public class AxeItemMixin extends ToolItem {
    public AxeItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Unique
    private final static List<Block> addedEffectiveBlocks = new ArrayList<>();

    static {

        addedEffectiveBlocks.add(Block.CRAFTING_TABLE);
        addedEffectiveBlocks.add(Block.NOTE_BLOCK);
        addedEffectiveBlocks.add(Block.JUKEBOX);
        addedEffectiveBlocks.add(Block.SIGN);
        addedEffectiveBlocks.add(Block.WALL_SIGN);
        addedEffectiveBlocks.add(Block.LADDER);
        addedEffectiveBlocks.add(Block.PUMPKIN);
        addedEffectiveBlocks.add(Block.JACK_O_LANTERN);
        addedEffectiveBlocks.add(Block.BRICKS);
        addedEffectiveBlocks.add(Block.RAIL);
        addedEffectiveBlocks.add(Block.POWERED_RAIL);
        addedEffectiveBlocks.add(Block.DETECTOR_RAIL);

    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (addedEffectiveBlocks.contains(block)) return this.miningSpeed;
        return super.getMiningSpeedMultiplier(stack, block);
    }
}
