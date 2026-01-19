package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class StairsBlockstateBuilder implements IJsonBuilder {

    protected Identifier model;
    protected String baseName;

    public StairsBlockstateBuilder(Identifier model, String baseName) {
        this.model = model;
        this.baseName = baseName;
    }
    public StairsBlockstateBuilder(String model, String baseName) {
        this.model = Identifier.of(model);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new VariantBlockstateBuilder()
                        .addVariant("facing=east,top=false", new VariantDataBuilder(this.model).getJson())
                        .addVariant("facing=west,top=false", new VariantDataBuilder(this.model).setY(180).setUVLock().getJson())
                        .addVariant("facing=south,top=false", new VariantDataBuilder(this.model).setY(90).setUVLock().getJson())
                        .addVariant("facing=north,top=false", new VariantDataBuilder(this.model).setY(270).setUVLock().getJson())
                        .addVariant("facing=east,top=true", new VariantDataBuilder(this.model).setX(180).setUVLock().getJson())
                        .addVariant("facing=west,top=true", new VariantDataBuilder(this.model).setX(180).setY(180).setUVLock().getJson())
                        .addVariant("facing=south,top=true", new VariantDataBuilder(this.model).setX(180).setY(90).setUVLock().getJson())
                        .addVariant("facing=north,top=true", new VariantDataBuilder(this.model).setX(180).setY(270).setUVLock().getJson())
                        .getFile(baseName)
        };
    }
}
