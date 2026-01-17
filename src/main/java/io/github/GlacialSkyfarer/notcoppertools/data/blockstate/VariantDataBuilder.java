package io.github.GlacialSkyfarer.notcoppertools.data.blockstate;

import com.google.gson.JsonObject;
import net.modificationstation.stationapi.api.util.Identifier;

public class VariantDataBuilder {
    protected Identifier model;
    protected int x;
    protected int y;
    protected boolean uvlock;
    protected int weight;

    public VariantDataBuilder(Identifier model) {
        this.model = model;
    }

    public VariantDataBuilder setX(int x) {
        this.x = x;
        return this;
    }
    public VariantDataBuilder setY(int y) {
        this.y = y;
        return this;
    }
    public VariantDataBuilder setUVLock() {
        this.uvlock = true;
        return this;
    }
    public VariantDataBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public JsonObject getJson() {
        JsonObject json = new JsonObject();

        json.addProperty("model", this.model.toString());
        if (this.x != 0) json.addProperty("x", this.x);
        if (this.y != 0) json.addProperty("y", this.y);
        if (this.uvlock) json.addProperty("uvlock", this.uvlock);
        if (this.weight != 0) json.addProperty("weight", this.weight);

        return json;
    }
}
