package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class TrapdoorBlockstateBuilder implements IJsonBuilder {

    protected Identifier baseModel;
    protected String baseName;

    public TrapdoorBlockstateBuilder(Identifier baseModel, String baseName) {
        this.baseModel = baseModel;
        this.baseName = baseName;
    }
    public TrapdoorBlockstateBuilder(String baseModel, String baseName) {
        this.baseModel = Identifier.of(baseModel);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new VariantBlockstateBuilder()
                        .addVariant("facing=south,top=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).getJson())
                        .addVariant("facing=north,top=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(180).getJson())
                        .addVariant("facing=west,top=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(90).getJson())
                        .addVariant("facing=east,top=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(270).getJson())
                        .addVariant("facing=south,top=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).getJson())
                        .addVariant("facing=north,top=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(180).getJson())
                        .addVariant("facing=west,top=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(90).getJson())
                        .addVariant("facing=east,top=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(270).getJson())
                        .addVariant("facing=south,top=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).getJson())
                        .addVariant("facing=north,top=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setY(180).getJson())
                        .addVariant("facing=west,top=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setY(90).getJson())
                        .addVariant("facing=east,top=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setY(270).getJson())
                        .addVariant("facing=south,top=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setX(180).setY(180).getJson())
                        .addVariant("facing=north,top=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setX(180).getJson())
                        .addVariant("facing=west,top=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setX(180).setY(270).getJson())
                        .addVariant("facing=east,top=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_open")).setX(180).setY(90).getJson())
                        .getFile(baseName)
        };
    }
}
