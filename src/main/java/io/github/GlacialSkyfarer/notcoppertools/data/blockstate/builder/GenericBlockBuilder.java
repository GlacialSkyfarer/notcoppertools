package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class GenericBlockBuilder implements IJsonBuilder {

    protected Identifier model;
    protected String baseName;

    public GenericBlockBuilder(Identifier model, String baseName) {
        this.model = model;
        this.baseName = baseName;
    }
    public GenericBlockBuilder(String model, String baseName) {
        this.model = Identifier.of(model);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new VariantBlockstateBuilder()
                        .addVariant("", new VariantDataBuilder(this.model).getJson())
                        .getFile(baseName)
        };
    }
}
