package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ShapedRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StairsRecipeBuilder implements IJsonBuilder {

    protected IRecipeIngredient ingredient;
    protected ItemStack result;
    protected String baseName;

    public StairsRecipeBuilder(IRecipeIngredient ingredient, ItemStack result, String baseName) {
        this.ingredient = ingredient;
        this.result = result;
        this.baseName = baseName;
    }
    public StairsRecipeBuilder(IRecipeIngredient ingredient, Item result, String baseName) {
        this.ingredient = ingredient;
        this.result = new ItemStack(result);
        this.baseName = baseName;
    }
    public StairsRecipeBuilder(IRecipeIngredient ingredient, Block result, String baseName) {
        this.ingredient = ingredient;
        this.result = new ItemStack(result.asItem());
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {

        JsonFile[] jsonFiles = new JsonFile[2];
        jsonFiles[0] = new ShapedRecipeBuilder()
                .setPattern(
                        "X  ",
                        "XX ",
                        "XXX"
                )
                .defineIngredient('X', this.ingredient)
                .setResult(this.result)
                .getFile(baseName + "_left");
        jsonFiles[1] = new ShapedRecipeBuilder()
                .setPattern(
                        "  X",
                        " XX",
                        "XXX"
                )
                .defineIngredient('X', this.ingredient)
                .setResult(this.result)
                .getFile(baseName + "_right");
        return jsonFiles;

    }
}
