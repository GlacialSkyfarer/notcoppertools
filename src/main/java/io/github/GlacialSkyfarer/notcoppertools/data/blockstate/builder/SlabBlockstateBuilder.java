package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class SlabBlockstateBuilder implements IJsonBuilder {

    protected Identifier model;
    protected String baseName;

    public SlabBlockstateBuilder(Identifier model, String baseName) {
        this.model = model;
        this.baseName = baseName;
    }
    public SlabBlockstateBuilder(String model, String baseName) {
        this.model = Identifier.of(model);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new VariantBlockstateBuilder()
                        .addVariant("type=bottom", new VariantDataBuilder(this.model).getJson())
                        .addVariant("type=top", new VariantDataBuilder(this.model.withSuffixedPath("_top")).getJson())
                        .addVariant("type=double", new VariantDataBuilder(this.model.withSuffixedPath("_double")).getJson())
                        .getFile(baseName)
        };
    }
}
