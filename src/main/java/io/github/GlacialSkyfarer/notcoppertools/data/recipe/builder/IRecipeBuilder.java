package io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;

public interface IRecipeBuilder {
    default JsonFile[] getFiles() {
        return null;
    }
}
