package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class FenceModelBuilder implements IJsonBuilder {

    protected Identifier texture;
    protected String baseName;

    public FenceModelBuilder(Identifier texture, String baseName) {
        this.texture = texture;
        this.baseName = baseName;
    }
    public FenceModelBuilder(String texture, String baseName) {
        this.texture = Identifier.of(texture);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/fence_post"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_post"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/fence_side"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_side"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/fence_inventory"))
                        .addTexture("texture", this.texture)
                        .getFile("block/" + this.baseName + "_inventory"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/" + this.baseName + "_inventory"))
                        .getFile("item/" + this.baseName)
        };
    }
}
