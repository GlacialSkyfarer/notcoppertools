package io.github.GlacialSkyfarer.gamma173.recipe;

import io.github.GlacialSkyfarer.gamma173.block.Blocks;
import io.github.GlacialSkyfarer.gamma173.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class StonecutterRecipeHandler {

    private static final List<StonecutterRecipe> recipeList = new ArrayList<>();

    public static ItemStack[] getItems(Item source) {
        for (StonecutterRecipe recipe : recipeList) {
            if (recipe.checkRecipe(source)) {
                ItemStack[] returns = new ItemStack[recipe.results.length];
                for (int i = 0; i < recipe.results.length; i++) {
                    returns[i] = recipe.results[i].copy();
                }
                return returns;
            }
        }
        return null;
    }

    public static void register(StonecutterRecipe recipe) {
        recipeList.add(recipe);
        //Add to AlwaysMoreItems
    }

    public static void registerRecipes() {
        register(new StonecutterRecipe(Block.STONE.asItem(), new ItemStack[]{
                new ItemStack(Blocks.STONE_BRICKS),
                new ItemStack(Blocks.CARVED_STONE),
                new ItemStack(Blocks.CHISELED_STONE),
                new ItemStack(Blocks.STONE_SLAB, 2)
            }
        ));
        register(new StonecutterRecipe(Blocks.CARVED_STONE.asItem(), new ItemStack[] {
                new ItemStack(Blocks.STONE_SLAB, 2)
        }));
        register(new StonecutterRecipe(Block.COBBLESTONE.asItem(), new ItemStack[]{
                new ItemStack(Blocks.COBBLESTONE_SLAB, 2),
                new ItemStack(Block.COBBLESTONE_STAIRS)
        }));
        register(new StonecutterRecipe(Block.BRICKS.asItem(), new ItemStack[]{
                new ItemStack(Blocks.BRICK_SLAB, 2)
        }));
        register(new StonecutterRecipe(Block.SANDSTONE.asItem(), new ItemStack[]{
                new ItemStack(Blocks.SANDSTONE_BRICKS),
                new ItemStack(Blocks.CARVED_SANDSTONE),
                new ItemStack(Blocks.CHISELED_SANDSTONE),
                new ItemStack(Blocks.SANDSTONE_SLAB, 2)
        }));
        register(new StonecutterRecipe(Blocks.SANDSTONE_BRICKS.asItem(), new ItemStack[]{
                new ItemStack(Blocks.SANDSTONE_BRICK_SLAB, 2)
        }));
        register(new StonecutterRecipe(Blocks.STONE_BRICKS.asItem(), new ItemStack[]{
                new ItemStack(Blocks.STONE_BRICK_SLAB, 2)
        }));
        register(new StonecutterRecipe(Block.NETHERRACK.asItem(), new ItemStack[]{
                new ItemStack(Blocks.NETHERRACK_BRICKS),
                new ItemStack(Blocks.CARVED_NETHERRACK)
        }));
        register(new StonecutterRecipe(Blocks.NETHERRACK_BRICKS.asItem(), new ItemStack[]{
                new ItemStack(Blocks.NETHERRACK_BRICK_SLAB, 2)
        }));
    }

}
