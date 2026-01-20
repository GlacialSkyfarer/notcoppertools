package io.github.GlacialSkyfarer.notcoppertools.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.EmmaTheMartian.ItemStackHelpers;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ShapelessRecipeBuilder implements IJsonBuilder {
    public static final Identifier TYPE_ID = Identifier.of("minecraft:crafting_shapeless");

    protected List<IRecipeIngredient> ingredients = new ArrayList<>();
    protected ItemStack result;
    protected String baseName;

    public ShapelessRecipeBuilder addIngredient(IRecipeIngredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }
    public ShapelessRecipeBuilder setResult(ItemStack result) {
        this.result = result.copy();
        return this;
    }
    public ShapelessRecipeBuilder setResult(Item result) {
        return this.setResult(new ItemStack(result, 1));
    }
    public ShapelessRecipeBuilder setResult(Block result) {
        return this.setResult(result.asItem());
    }
    public ShapelessRecipeBuilder setBaseName(String baseName) {
        this.baseName = baseName;
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject json = new JsonObject();

        json.addProperty("type", TYPE_ID.toString());

        JsonArray ingredientsArray = new JsonArray();
        for (IRecipeIngredient ingredient : ingredients) {
            ingredientsArray.add(ingredient.getJson());
        }
        json.add("ingredients", ingredientsArray);

        json.add("result", ItemStackHelpers.stackToJson(result));

        return JsonFile.of(name, json);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] { getFile(baseName) };
    }
}
