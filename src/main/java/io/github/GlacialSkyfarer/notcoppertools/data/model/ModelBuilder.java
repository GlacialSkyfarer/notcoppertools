package io.github.GlacialSkyfarer.notcoppertools.data.model;

import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModelBuilder implements IJsonBuilder {
    protected Identifier parent;
    protected Map<String, Identifier> textures = new HashMap<>();
    protected String baseName;

    public ModelBuilder setParent(Identifier value) {
        this.parent = value;
        return this;
    }
    public ModelBuilder addTexture(String name, Identifier texture) {
        this.textures.put(name, texture);
        return this;
    }
    public ModelBuilder setBaseName(String name) {
        this.baseName = name;
        return this;
    }

    public JsonFile getFile(String name) {
        JsonObject object = new JsonObject();

        object.addProperty("parent", this.parent.toString());

        if (!this.textures.isEmpty()) {
            JsonObject texturesObject = new JsonObject();
            this.textures.forEach((key, texture) -> texturesObject.addProperty(key, texture.toString()));
            object.add("textures", texturesObject);
        }

        return JsonFile.of(name, object);
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                getFile(baseName)
        };
    }
}
