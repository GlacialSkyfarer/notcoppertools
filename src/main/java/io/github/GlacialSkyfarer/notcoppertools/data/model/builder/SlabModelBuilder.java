package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.model.ModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class SlabModelBuilder implements IJsonBuilder {

    protected Identifier side;
    protected Identifier top;
    protected Identifier bottom;
    protected String baseName;

    public SlabModelBuilder(Identifier side, Identifier top, Identifier bottom, String baseName) {
        this.side = side;
        this.top = top;
        this.bottom = bottom;
        this.baseName = baseName;
    }
    public SlabModelBuilder(String side, String top, String bottom, String baseName) {
        this.side = Identifier.of(side);
        this.top = Identifier.of(top);
        this.bottom = Identifier.of(bottom);
        this.baseName = baseName;
    }
    public SlabModelBuilder(Identifier side, Identifier end, String baseName) {
        this.side = side;
        this.top = this.bottom = end;
        this.baseName = baseName;
    }
    public SlabModelBuilder(String side, String end, String baseName) {
        this.side = Identifier.of(side);
        this.top = this.bottom = Identifier.of(end);
        this.baseName = baseName;
    }
    public SlabModelBuilder(Identifier texture, String baseName) {
        this.side = this.top = this.bottom = texture;
        this.baseName = baseName;
    }
    public SlabModelBuilder(String texture, String baseName) {
        this.side = this.top = this.bottom = Identifier.of(texture);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/slab"))
                        .addTexture("side", this.side)
                        .addTexture("top", this.top)
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/slab_top"))
                        .addTexture("side", this.side)
                        .addTexture("top", this.top)
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName + "_top"),
                new ModelBuilder()
                        .setParent(Identifier.of("block/cube_bottom_top"))
                        .addTexture("side", this.side)
                        .addTexture("top", this.top)
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName + "_double"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/" + this.baseName))
                        .getFile("item/" + this.baseName)
        };
    }
}
