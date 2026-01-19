package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.model.ModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class GratingModelBuilder implements IJsonBuilder {

    protected Identifier side;
    protected Identifier top;
    protected String baseName;

    public GratingModelBuilder(Identifier side, Identifier top, String baseName) {
        this.side = side;
        this.top = top;
        this.baseName = baseName;
    }
    public GratingModelBuilder(String side, String top, String baseName) {
        this.side = Identifier.of(side);
        this.top = Identifier.of(top);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/grating_post"))
                        .addTexture("texture", this.top)
                        .getFile("block/" + this.baseName + "_post"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/grating_cap"))
                        .addTexture("texture", this.top)
                        .getFile("block/" + this.baseName + "_cap"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/grating_side"))
                        .addTexture("side", this.side)
                        .addTexture("top", this.top)
                        .getFile("block/" + this.baseName + "_side"),
                new ModelBuilder()
                        .setParent(Identifier.of("minecraft:item/generated"))
                        .addTexture("layer0", this.side)
                        .getFile("item/" + this.baseName)
        };
    }
}
