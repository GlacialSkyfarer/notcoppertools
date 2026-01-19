package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.model.ModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.IJsonBuilder;
import net.modificationstation.stationapi.api.util.Identifier;

public class DoorModelBuilder implements IJsonBuilder {

    protected Identifier bottom;
    protected Identifier top;
    protected Identifier item;
    protected String baseName;

    public DoorModelBuilder(Identifier bottom, Identifier top, Identifier item, String baseName) {
        this.bottom = bottom;
        this.top = top;
        this.item = item;
        this.baseName = baseName;
    }
    public DoorModelBuilder(String bottom, String top, String item, String baseName) {
        this.bottom = Identifier.of(bottom);
        this.top = Identifier.of(top);
        this.item = Identifier.of(item);
        this.baseName = baseName;
    }

    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/door_bottom"))
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName + "_bottom"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/door_bottom_rh"))
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName + "_bottom_rh"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/door_top"))
                        .addTexture("top", this.top)
                        .getFile("block/" + this.baseName + "_top"),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/door_top_rh"))
                        .addTexture("top", this.top)
                        .getFile("block/" + this.baseName + "_top_rh"),
                new ModelBuilder()
                        .setParent(Identifier.of("item/generated"))
                        .addTexture("layer0", this.item)
                        .getFile("item/" + this.baseName)
        };
    }
}
