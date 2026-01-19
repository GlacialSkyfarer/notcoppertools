package io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.MultipartPartBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.VariantDataBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class GratingBlockstateBuilder implements IJsonBuilder {

    protected Identifier baseModel;
    protected String baseName;

    public GratingBlockstateBuilder(Identifier baseModel, String baseName) {
        this.baseModel = baseModel;
        this.baseName = baseName;
    }
    public GratingBlockstateBuilder(String baseModel, String baseName) {
        this.baseModel = Identifier.of(baseModel);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new MultipartBlockstateBuilder()
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_post")).getJson())
                                        .addPredicate("north", "false")
                                        .addPredicate("south", "false")
                                        .addPredicate("east", "false")
                                        .addPredicate("west", "false")
                                        .getJson())
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_side")).setUVLock().getJson())
                                        .addPredicate("north", "true")
                                        .getJson())
                        .addPart(
                        new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_side")).setY(90).setUVLock().getJson())
                                .addPredicate("east", "true")
                                .getJson())
                        .addPart(
                        new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_side")).setY(180).setUVLock().getJson())
                                .addPredicate("south", "true")
                                .getJson())
                        .addPart(
                        new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_side")).setY(270).setUVLock().getJson())
                                .addPredicate("west", "true")
                                .getJson())
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_cap")).setUVLock().getJson())
                                        .addPredicate("north", "true")
                                        .addPredicate("south", "false")
                                        .addPredicate("east", "false")
                                        .addPredicate("west", "false")
                                        .getJson())
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_cap")).setY(180).setUVLock().getJson())
                                        .addPredicate("north", "false")
                                        .addPredicate("south", "true")
                                        .addPredicate("east", "false")
                                        .addPredicate("west", "false")
                                        .getJson())
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_cap")).setY(90).setUVLock().getJson())
                                        .addPredicate("north", "false")
                                        .addPredicate("south", "false")
                                        .addPredicate("east", "true")
                                        .addPredicate("west", "false")
                                        .getJson())
                        .addPart(
                                new MultipartPartBuilder(new VariantDataBuilder(baseModel.withSuffixedPath("_cap")).setY(270).setUVLock().getJson())
                                        .addPredicate("north", "false")
                                        .addPredicate("south", "false")
                                        .addPredicate("east", "false")
                                        .addPredicate("west", "true")
                                        .getJson())
                        .getFile(baseName)
        };
    }
}
