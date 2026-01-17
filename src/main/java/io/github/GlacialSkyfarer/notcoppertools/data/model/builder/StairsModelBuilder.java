package io.github.GlacialSkyfarer.notcoppertools.data.model.builder;

import io.github.GlacialSkyfarer.notcoppertools.data.JsonFile;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IRecipeIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.ShapedRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class StairsModelBuilder implements IJsonBuilder {

    protected Identifier side;
    protected Identifier top;
    protected Identifier bottom;
    protected String baseName;

    public StairsModelBuilder(Identifier side, Identifier top, Identifier bottom, String baseName) {
        this.side = side;
        this.top = top;
        this.bottom = bottom;
        this.baseName = baseName;
    }
    public StairsModelBuilder(String side, String top, String bottom, String baseName) {
        this.side = Identifier.of(side);
        this.top = Identifier.of(top);
        this.bottom = Identifier.of(bottom);
        this.baseName = baseName;
    }
    public StairsModelBuilder(Identifier side, Identifier end, String baseName) {
        this.side = side;
        this.top = this.bottom = end;
        this.baseName = baseName;
    }
    public StairsModelBuilder(String side, String end, String baseName) {
        this.side = Identifier.of(side);
        this.top = this.bottom = Identifier.of(end);
        this.baseName = baseName;
    }
    public StairsModelBuilder(Identifier texture, String baseName) {
        this.side = this.top = this.bottom = texture;
        this.baseName = baseName;
    }
    public StairsModelBuilder(String texture, String baseName) {
        this.side = this.top = this.bottom = Identifier.of(texture);
        this.baseName = baseName;
    }


    @Override
    public JsonFile[] getFiles() {
        return new JsonFile[] {
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/stairs"))
                        .addTexture("side", this.side)
                        .addTexture("top", this.top)
                        .addTexture("bottom", this.bottom)
                        .getFile("block/" + this.baseName),
                new ModelBuilder()
                        .setParent(Identifier.of("notcoppertools:block/" + this.baseName))
                        .getFile("item/" + this.baseName)
        };
    }
}
