package io.github.GlacialSkyfarer.notcoppertools.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StonecutterRecipe {

    public final Item source;
    public final ItemStack[] results;

    public StonecutterRecipe(Item source, ItemStack[] results) {
        this.source = source;
        this.results = results;
    }

    public boolean checkRecipe(Item source) {
        return this.source == source;
    }

}
