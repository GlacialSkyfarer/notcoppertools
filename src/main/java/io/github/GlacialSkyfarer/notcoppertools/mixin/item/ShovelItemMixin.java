package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(ShovelItem.class)
public class ShovelItemMixin extends ToolItem {
    public ShovelItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Unique
    private final static List<Block> addedEffectiveBlocks = new ArrayList<>();

    static {

        addedEffectiveBlocks.add(Block.SOUL_SAND);

    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (addedEffectiveBlocks.contains(block)) return this.miningSpeed;
        return super.getMiningSpeedMultiplier(stack, block);
    }
}
