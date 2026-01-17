package io.github.GlacialSkyfarer.notcoppertools.data.recipe;

import com.google.gson.JsonObject;

public interface IRecipeIngredient {
    default JsonObject getJson() {
        return null;
    }
}
