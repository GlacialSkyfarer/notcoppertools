package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import com.google.gson.JsonObject;
import io.github.EmmaTheMartian.ItemStackHelpers;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class FurnaceRecipeBuilder implements IJsonBuilder {
    public static final Identifier TYPE_ID = Identifier.of("minecraft:smelting");

    protected IRecipeIngredient ingredient;
    protected ItemStack result;
    protected String baseName;

    public FurnaceRecipeBuilder setIngredient(IRecipeIngredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }
    public FurnaceRecipeBuilder setResult(ItemStack result) {
        this.result = result.copy();
        return this;
    }
    public FurnaceRecipeBuilder setResult(Item result) {
        return this.setResult(new ItemStack(result, 6));
    }
    public FurnaceRecipeBuilder setResult(Block result) {
        return this.setResult(result.asItem());
    }
    public FurnaceRecipeBuilder setBaseName(String baseName) {
        this.baseName = baseName;
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject json = new JsonObject();
        json.addProperty("type", TYPE_ID.toString());
        json.add("ingredient", this.ingredient.getJson());
        json.add("result", ItemStackHelpers.stackToJson(result));
        return JsonFile.of(name, json);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] { getFile(baseName) };
    }
}
