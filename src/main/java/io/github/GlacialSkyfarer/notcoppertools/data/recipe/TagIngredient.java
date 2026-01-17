package io.github.GlacialSkyfarer.notcoppertools.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;

public class TagIngredient implements IRecipeIngredient {

    protected String tag;
    protected int count;

    protected TagIngredient(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }
    public static TagIngredient of(String tag, int count) {
        return new TagIngredient(tag, count);
    }
    public static TagIngredient of(TagKey<Item> tag, int count) {
        return new TagIngredient(tag.id().toString(), count);
    }
    public static TagIngredient of(String tag) {
        return of(tag, 1);
    }
    public static TagIngredient of(TagKey<Item> tag) {
        return of(tag, 1);
    }

    @Override
    public JsonObject getJson() {
        JsonObject result = new JsonObject();

        result.addProperty("tag", tag);
        if (count > 1) result.addProperty("count", count);

        return result;
    }
}
