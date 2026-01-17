package io.github.GlacialSkyfarer.notcoppertools.data.recipe;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;

public interface IJsonBuilder {
    default JsonFile[] getFiles() {
        return null;
    }
}
