package io.github.GlacialSkyfarer.notcoppertools.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;

public class ItemIngredient implements IRecipeIngredient {

    protected Item item;
    protected int count;
    protected int damage;

    protected ItemIngredient(Item item, int count, int damage) {
        this.item = item;
        this.count = count;
        this.damage = damage;
    }
    public static ItemIngredient of(Item item, int count, int damage) {
        return new ItemIngredient(item, count, damage);
    }
    public static ItemIngredient of(Block block, int count, int damage) {
        return new ItemIngredient(block.asItem(), count, damage);
    }
    public static ItemIngredient of(Item item, int count) {
        return of(item, count, -1);
    }
    public static ItemIngredient of(Block block, int count) {
        return of(block, count, -1);
    }
    public static ItemIngredient of(Item item) {
        return of(item, 1, -1);
    }
    public static ItemIngredient of(Block block) {
        return of(block, 1, -1);
    }

    @Override
    public JsonObject getJson() {
        JsonObject result = new JsonObject();

        result.addProperty("item", ItemRegistry.INSTANCE.getId(item).toString());
        if (count > 1) result.addProperty("count", count);
        if (damage > -1) result.addProperty("damage", damage);

        return result;
    }
}
