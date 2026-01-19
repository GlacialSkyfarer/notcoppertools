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

import java.util.*;

public class ShapedRecipeBuilder implements IJsonBuilder {
    public static final Identifier TYPE_ID = Identifier.of("minecraft:crafting_shaped");

    protected List<String> pattern = new ArrayList<>();
    protected Map<Character, IRecipeIngredient> key = new HashMap<>();
    protected ItemStack result;
    protected String baseName;

    public ShapedRecipeBuilder setPattern(String... patterns) {
        this.pattern.addAll(Arrays.stream(patterns).toList());
        return this;
    }
    public ShapedRecipeBuilder setResult(ItemStack result) {
        this.result = result.copy();
        return this;
    }
    public ShapedRecipeBuilder setResult(Item result) {
        return this.setResult(new ItemStack(result, 6));
    }
    public ShapedRecipeBuilder setResult(Block result) {
        return this.setResult(result.asItem());
    }
    public ShapedRecipeBuilder setBaseName(String baseName) {
        this.baseName = baseName;
        return this;
    }
    public ShapedRecipeBuilder defineIngredient(char key, IRecipeIngredient ingredient) {
        this.key.put(key, ingredient);
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject json = new JsonObject();

        json.addProperty("type", TYPE_ID.toString());

        JsonArray patternArray = new JsonArray();
        for (String line : pattern) {
            patternArray.add(line);
        }
        json.add("pattern", patternArray);

        JsonObject keyObject = new JsonObject();
        key.forEach((ch, ing) -> keyObject.add(ch.toString(), ing.getJson()));
        json.add("key", keyObject);

        json.add("result", ItemStackHelpers.stackToJson(result));

        return JsonFile.of(name, json);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] { getFile(baseName) };
    }
}
