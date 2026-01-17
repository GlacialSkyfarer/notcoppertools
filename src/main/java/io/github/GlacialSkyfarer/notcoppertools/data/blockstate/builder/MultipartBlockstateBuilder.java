package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipartBlockstateBuilder implements IJsonBuilder {
    protected List<JsonObject> parts = new ArrayList<>();
    protected String baseName;

    public MultipartBlockstateBuilder setBaseName(String name) {
        this.baseName = name;
        return this;
    }
    public MultipartBlockstateBuilder addPart(JsonObject part) {
        this.parts.add(part);
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject json = new JsonObject();

        JsonArray partsArray = new JsonArray();
        parts.forEach(partsArray::add);
        json.add("multipart", partsArray);

        return JsonFile.of(name, json);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                getFile(baseName)
        };
    }
}
