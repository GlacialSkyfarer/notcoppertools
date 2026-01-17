package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlabRecipeBuilder implements IRecipeBuilder {

    protected IRecipeIngredient ingredient;
    protected ItemStack result;
    protected String baseName;

    public SlabRecipeBuilder(IRecipeIngredient ingredient, ItemStack result, String baseName) {
        this.ingredient = ingredient;
        this.result = result;
        this.baseName = baseName;
    }
    public SlabRecipeBuilder(IRecipeIngredient ingredient, Item result, String baseName) {
        this.ingredient = ingredient;
        this.result = new ItemStack(result);
        this.baseName = baseName;
    }
    public SlabRecipeBuilder(IRecipeIngredient ingredient, Block result, String baseName) {
        this.ingredient = ingredient;
        this.result = new ItemStack(result.asItem());
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new ShapedRecipeBuilder()
                .setPattern(
                        "XXX"
                )
                .defineIngredient('X', this.ingredient)
                .setResult(this.result)
                .setBaseName(this.baseName)
                .getFiles();
    }
}
