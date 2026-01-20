package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ItemIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ShapedRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ShapelessRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DyeRecipeBuilder implements IJsonBuilder {

    protected static Item[] dyes = new Item[] {
            Items.BONE_MEAL,
            Items.INK_SAC,
            Items.GRAY_DYE,
            Items.LIGHT_GRAY_DYE,
            Items.COCOA_BEANS,
            Items.ROSE_RED,
            Items.ORANGE_DYE,
            Items.DANDELION_YELLOW,
            Items.LIME_DYE,
            Items.CACTUS_GREEN,
            Items.CYAN_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.LAPIS_LAZULI,
            Items.PURPLE_DYE,
            Items.MAGENTA_DYE,
            Items.PINK_DYE
    };
    protected static String[] prefixes = new String[] {
            "white_",
            "black_",
            "gray_",
            "light_gray_",
            "brown_",
            "red_",
            "orange_",
            "yellow_",
            "lime_",
            "green_",
            "cyan_",
            "light_blue_",
            "blue_",
            "purple_",
            "magenta_",
            "pink_"
    };

    public enum RecipeType {
        SHAPELESS,
        FOUR,
        EIGHT
    }

    protected IRecipeIngredient ingredient;
    protected Item[] results = new Item[16];
    protected RecipeType type;
    protected String baseName;

    public DyeRecipeBuilder(IRecipeIngredient ingredient, Item[] results, RecipeType type, String baseName) {
        if (results.length != 16) NotCopperTools.LOGGER.error("DyeRecipeBuilder results array must be [16]!");
        this.ingredient = ingredient;
        this.results = results;
        this.type = type;
        this.baseName = baseName;
    }
    public DyeRecipeBuilder(IRecipeIngredient ingredient, Block[] results, RecipeType type, String baseName) {
        if (results.length != 16) NotCopperTools.LOGGER.error("DyeRecipeBuilder results array must be [16]!");
        this.ingredient = ingredient;
        for (int i = 0; i < 16; i++) {
            this.results[i] = results[i].asItem();
        }
        this.type = type;
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        JsonFile[] files = new JsonFile[16];
        for (int i = 0; i < 16; i++) {
            switch (this.type) {
                case SHAPELESS:
                    files[i] = new ShapelessRecipeBuilder()
                            .addIngredient(ingredient)
                            .addIngredient(ItemIngredient.of(dyes[i]))
                            .setResult(new ItemStack(results[i], 1))
                            .getFile(prefixes[i] + baseName);
                    break;
                case FOUR:
                    files[i] = new ShapedRecipeBuilder()
                            .defineIngredient('I', ingredient)
                            .defineIngredient('D', ItemIngredient.of(dyes[i]))
                            .setPattern(
                                    " I ",
                                    "IDI",
                                    " I "
                            )
                            .setResult(new ItemStack(results[i], 4))
                            .getFile(prefixes[i] + baseName);
                    break;
                case EIGHT:
                    files[i] = new ShapedRecipeBuilder()
                            .defineIngredient('I', ingredient)
                            .defineIngredient('D', ItemIngredient.of(dyes[i]))
                            .setPattern(
                                    "III",
                                    "IDI",
                                    "III"
                            )
                            .setResult(new ItemStack(results[i], 8))
                            .getFile(prefixes[i] + baseName);
                    break;
            }
        }
        return files;
    }
}
