package io.github.GlacialSkyfarer.notcoppertools.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AnvilRecipe {

    public final Item source;
    public final Item material;
    public final ItemStack result;

    public AnvilRecipe(Item source, Item material, ItemStack result) {
        this.source = source;
        this.material = material;
        this.result = result;
    }

    public boolean checkRecipe(Item source, Item material) {
        return this.source == source && this.material == material;
    }

}
