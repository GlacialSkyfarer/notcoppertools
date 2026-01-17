package io.github.GlacialSkyfarer.notcoppertools.data.blockstate;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class MultipartPartBuilder {
    protected Map<String, String> predicates = new HashMap<>();
    protected JsonObject variant;

    public MultipartPartBuilder(JsonObject variant) {
        this.variant = variant;
    }

    public MultipartPartBuilder addPredicate(String property, String value) {
        this.predicates.put(property, value);
        return this;
    }

    public JsonObject getJson() {

        JsonObject json = new JsonObject();
        if (!predicates.isEmpty()) {
            JsonObject when = new JsonObject();
            predicates.forEach(when::addProperty);
            json.add("when", when);
        }

        json.add("apply", variant);
        return json;
    }
}
