package io.github.GlacialSkyfarer.notcoppertools.recipe;

import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class AnvilRecipeHandler {

    private static final List<AnvilRecipe> recipeList = new ArrayList<>();

    public static ItemStack getItem(Item source, Item material) {
        for (AnvilRecipe recipe : recipeList) {
            if (recipe.checkRecipe(source, material)) {
                return recipe.result.copy();
            }
        }
        return null;
    }

    public static void register(AnvilRecipe recipe) {
        recipeList.add(recipe);
        //Add to AlwaysMoreItems
    }

    public static void registerRecipes() {
        register(new AnvilRecipe(Item.GOLDEN_SWORD, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_SWORD)));
        register(new AnvilRecipe(Item.GOLDEN_PICKAXE, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_PICKAXE)));
        register(new AnvilRecipe(Item.GOLDEN_AXE, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_AXE)));
        register(new AnvilRecipe(Item.GOLDEN_SHOVEL, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_SHOVEL)));
        register(new AnvilRecipe(Item.GOLDEN_HOE, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_HOE)));

        register(new AnvilRecipe(Item.GOLDEN_HELMET, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_HELMET)));
        register(new AnvilRecipe(Item.GOLDEN_CHESTPLATE, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_CHESTPLATE)));
        register(new AnvilRecipe(Item.GOLDEN_LEGGINGS, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_LEGGINGS)));
        register(new AnvilRecipe(Item.GOLDEN_BOOTS, Items.COPPER_INGOT, new ItemStack(Items.ROSE_GOLD_BOOTS)));
    }

}
