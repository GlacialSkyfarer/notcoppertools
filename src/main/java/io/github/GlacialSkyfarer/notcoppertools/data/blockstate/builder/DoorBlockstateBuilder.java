package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class DoorBlockstateBuilder implements IJsonBuilder {

    protected Identifier baseModel;
    protected String baseName;

    public DoorBlockstateBuilder(Identifier baseModel, String baseName) {
        this.baseModel = baseModel;
        this.baseName = baseName;
    }
    public DoorBlockstateBuilder(String baseModel, String baseName) {
        this.baseModel = Identifier.of(baseModel);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new VariantBlockstateBuilder()
                        .addVariant("facing=east,top=false,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).getJson())
                        .addVariant("facing=south,top=false,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(90).getJson())
                        .addVariant("facing=west,top=false,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(180).getJson())
                        .addVariant("facing=north,top=false,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(270).getJson())
                        .addVariant("facing=east,top=false,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).getJson())
                        .addVariant("facing=south,top=false,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(90).getJson())
                        .addVariant("facing=west,top=false,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(180).getJson())
                        .addVariant("facing=north,top=false,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(270).getJson())
                        .addVariant("facing=east,top=false,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(90).getJson())
                        .addVariant("facing=south,top=false,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(180).getJson())
                        .addVariant("facing=west,top=false,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).setY(270).getJson())
                        .addVariant("facing=north,top=false,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom_rh")).getJson())
                        .addVariant("facing=east,top=false,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(270).getJson())
                        .addVariant("facing=south,top=false,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).getJson())
                        .addVariant("facing=west,top=false,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(90).getJson())
                        .addVariant("facing=north,top=false,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_bottom")).setY(180).getJson())
                        .addVariant("facing=east,top=true,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).getJson())
                        .addVariant("facing=south,top=true,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(90).getJson())
                        .addVariant("facing=west,top=true,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(180).getJson())
                        .addVariant("facing=north,top=true,flipped=false,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(270).getJson())
                        .addVariant("facing=east,top=true,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).getJson())
                        .addVariant("facing=south,top=true,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(90).getJson())
                        .addVariant("facing=west,top=true,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(180).getJson())
                        .addVariant("facing=north,top=true,flipped=true,open=false", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(270).getJson())
                        .addVariant("facing=east,top=true,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(90).getJson())
                        .addVariant("facing=south,top=true,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(180).getJson())
                        .addVariant("facing=west,top=true,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).setY(270).getJson())
                        .addVariant("facing=north,top=true,flipped=false,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top_rh")).getJson())
                        .addVariant("facing=east,top=true,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(270).getJson())
                        .addVariant("facing=south,top=true,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).getJson())
                        .addVariant("facing=west,top=true,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(90).getJson())
                        .addVariant("facing=north,top=true,flipped=true,open=true", new VariantDataBuilder(this.baseModel.withSuffixedPath("_top")).setY(180).getJson())
                        .getFile(baseName)
        };
    }
}
