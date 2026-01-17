package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ItemIngredient;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreRecipeSetBuilder implements IRecipeBuilder {

    protected ItemStack ore;
    protected ItemStack ingot;
    protected ItemStack block;

    protected String baseName;

    public OreRecipeSetBuilder(ItemStack ore, ItemStack ingot, ItemStack block, String baseName) {
        this.ore = ore;
        this.ingot = ingot;
        this.block = block;
        this.baseName = baseName;
    }
    public OreRecipeSetBuilder(Block ore, Item ingot, Block block, String baseName) {
        this.ore = new ItemStack(ore);
        this.ingot = new ItemStack(ingot);
        this.block = new ItemStack(block);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        ItemStack nineIngots = this.ingot.copy();
        nineIngots.count = 9;
        return new JsonFile[] {
                new ShapedRecipeBuilder()
                        .setPattern(
                                "XXX",
                                "XXX",
                                "XXX"
                        )
                        .defineIngredient('X', ItemIngredient.of(this.ingot.getItem()))
                        .setResult(this.block)
                        .getFile(this.baseName + "_block"),
                new ShapelessRecipeBuilder()
                        .addIngredient(ItemIngredient.of(this.block.getItem()))
                        .setResult(nineIngots)
                        .getFile(this.baseName + "_ingot_extraction"),
                new FurnaceRecipeBuilder()
                        .setIngredient(ItemIngredient.of(this.ore.getItem()))
                        .setResult(this.ingot)
                        .getFile(this.baseName + "_ingot_smelting"),
        };
    }
}
