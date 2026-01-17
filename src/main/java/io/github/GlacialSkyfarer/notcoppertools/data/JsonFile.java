package io.github.GlacialSkyfarer.notcoppertools.data;

import com.google.gson.JsonObject;

public class JsonFile {

    public String name = "";
    public JsonObject json;

    protected JsonFile(String name, JsonObject json) {
        this.name = name;
        this.json = json;
    }

    public static JsonFile of(String name, JsonObject json) {
        return new JsonFile(name, json);
    }

}
