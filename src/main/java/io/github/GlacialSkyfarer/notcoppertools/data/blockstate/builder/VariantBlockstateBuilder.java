package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class VariantBlockstateBuilder implements IJsonBuilder {
    protected Map<String, JsonObject> variants = new HashMap<>();
    protected String baseName;

    public VariantBlockstateBuilder setBaseName(String name) {
        this.baseName = name;
        return this;
    }
    public VariantBlockstateBuilder addVariant(String predicate, JsonObject variant) {
        this.variants.put(predicate, variant);
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject object = new JsonObject();

        JsonObject variantsObject = new JsonObject();
        variants.forEach(variantsObject::add);
        object.add("variants", variantsObject);

        return JsonFile.of(name, object);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                getFile(baseName)
        };
    }
}
