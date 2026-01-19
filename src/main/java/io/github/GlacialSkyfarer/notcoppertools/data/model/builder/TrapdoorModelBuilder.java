package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class TrapdoorModelBuilder implements IJsonBuilder {

    protected Identifier texture;
    protected String baseName;

    public TrapdoorModelBuilder(Identifier texture, String baseName) {
        this.texture = texture;
        this.baseName = baseName;
    }
    public TrapdoorModelBuilder(String texture, String baseName) {
        this.texture = Identifier.of(texture);
        this.baseName = baseName;
    }


    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/template_orientable_trapdoor_bottom"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_bottom"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/template_orientable_trapdoor_open"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_open"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/template_orientable_trapdoor_top"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_top"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/" + this.baseName + "_bottom"))
                        .getFile("item/" + this.baseName)
        };
    }
}
