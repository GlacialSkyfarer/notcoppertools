package io.github.GlacialSkyfarer.notcoppertools.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder.FenceBlockstateBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder.StairsBlockstateBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.model.builder.FenceModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.model.builder.GratingModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.model.builder.SlabModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.model.builder.StairsModelBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ItemIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.IJsonBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.OreRecipeSetBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.SlabRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.StairsRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NCTDatagen {

    public static void dataGen() {
        boolean shouldRun = System.getProperty("runDatagen") != null;
        if (!shouldRun) return;
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
            NotCopperTools.LOGGER.error("Datagen shouldn't be running outside of a dev environment!");
            System.exit(1);
        }

        NotCopperTools.LOGGER.info("Beginning datagen for It's Not Copper Tools!");
        NotCopperTools.LOGGER.info("The game will close after datagen is complete.");

        Path targetPath = getTargetPath();
        if (Files.exists(targetPath)) {
            NotCopperTools.LOGGER.info("Cleansing generated directory.");
            try {
                FileUtils.deleteDirectory(targetPath.toFile());
            } catch(Exception e) {
                NotCopperTools.LOGGER.error(e.getMessage());
            }
        }

        populateRecipes();
        NotCopperTools.LOGGER.info("Recipes done.");
        populateModels();
        NotCopperTools.LOGGER.info("Models done.");
        populateBlockstates();
        NotCopperTools.LOGGER.info("Blockstates done.");

        NotCopperTools.LOGGER.info("Datagen finished! Exiting.");
        System.exit(0);
    }

    protected static Path getTargetPath() {
        String strPath = Objects.requireNonNull(System.getProperty("datagen.path"), "System property datagen.path was null. Make sure it is defined in your buildscript.");
        return Path.of(strPath);
    }

    protected static Path getTargetPath(String parent, String... subfolders) {
        Path path = getTargetPath().resolve(parent).resolve(NotCopperTools.NAMESPACE.toString()).resolve("stationapi");
        for (String sub : subfolders) {
            path = path.resolve(sub);
        }
        return path;
    }

    protected static void save(Path path, String data) {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, List.of(data), StandardCharsets.UTF_8);
        } catch (IOException e) {
            NotCopperTools.LOGGER.error(e.getMessage());
        }
    }

    protected static void save(Path path, JsonObject object) {
        save(path, new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(object));
    }

    protected static List<IJsonBuilder> recipeBuilders = new ArrayList<>();
    protected static void populateRecipes() {

        //region stairs
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Block.PLANKS),
                        new ItemStack(Blocks.OAK_STAIRS, 6),
                        "oak_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.CONIFER_PLANKS),
                        new ItemStack(Blocks.CONIFER_STAIRS, 6),
                        "conifer_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.BIRCH_PLANKS),
                        new ItemStack(Blocks.BIRCH_STAIRS, 6),
                        "birch_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Block.COBBLESTONE),
                        new ItemStack(Blocks.COBBLESTONE_STAIRS, 6),
                        "cobblestone_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_STONE),
                        new ItemStack(Blocks.STONE_STAIRS, 6),
                        "stone_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_SANDSTONE),
                        new ItemStack(Blocks.SANDSTONE_STAIRS, 6),
                        "sandstone_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.STONE_BRICKS),
                        new ItemStack(Blocks.STONE_BRICK_STAIRS, 6),
                        "stone_brick_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.SANDSTONE_BRICKS),
                        new ItemStack(Blocks.SANDSTONE_BRICK_STAIRS, 6),
                        "sandstone_brick_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Block.BRICKS),
                        new ItemStack(Blocks.BRICK_STAIRS, 6),
                        "brick_stairs"
                )
        );
        //endregion
        //region slabs
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Block.PLANKS),
                        new ItemStack(Blocks.OAK_SLAB, 6),
                        "oak_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.CONIFER_PLANKS),
                        new ItemStack(Blocks.CONIFER_SLAB, 6),
                        "conifer_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.BIRCH_PLANKS),
                        new ItemStack(Blocks.BIRCH_SLAB, 6),
                        "birch_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Block.COBBLESTONE),
                        new ItemStack(Blocks.COBBLESTONE_SLAB, 6),
                        "cobblestone_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Block.STONE),
                        new ItemStack(Blocks.STONE_SLAB, 6),
                        "stone_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_STONE),
                        new ItemStack(Blocks.STONE_SLAB, 6),
                        "stone_slab_from_carved"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_SANDSTONE),
                        new ItemStack(Blocks.SANDSTONE_SLAB, 6),
                        "sandstone_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.STONE_BRICKS),
                        new ItemStack(Blocks.STONE_BRICK_SLAB, 6),
                        "stone_brick_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.SANDSTONE_BRICKS),
                        new ItemStack(Blocks.SANDSTONE_BRICK_SLAB, 6),
                        "sandstone_brick_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Block.BRICKS),
                        new ItemStack(Blocks.BRICK_SLAB, 6),
                        "brick_slab"
                )
        );
        //endregion
        //region ores
        recipeBuilders.add(
                new OreRecipeSetBuilder(Blocks.COPPER_ORE, Items.COPPER_INGOT, Blocks.COPPER_BLOCK, "copper")
        );
        //endregion

        for (IJsonBuilder recipe : recipeBuilders) {
            JsonFile[] files = recipe.getFiles();

            for (JsonFile file : files) {
                save(getTargetPath("data", "recipes").resolve(file.name + ".json"), file.json);
            }
        }
    }

    protected static List<IJsonBuilder> modelBuilders = new ArrayList<>();
    protected static void populateModels() {

        //region stairs
        modelBuilders.add(
                new StairsModelBuilder("minecraft:block/oak_planks", "oak_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/conifer_planks", "conifer_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/birch_planks", "birch_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder("minecraft:block/cobblestone", "cobblestone_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder(
                        "minecraft:block/smooth_stone_slab_side",
                        "minecraft:block/smooth_stone",
                        "stone_stairs"
                )
        );
        modelBuilders.add(
                new StairsModelBuilder(
                        "notcoppertools:block/sandstone_slab_side",
                        "notcoppertools:block/carved_sandstone",
                        "sandstone_stairs"
                )
        );
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/stone_bricks", "stone_brick_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/sandstone_bricks", "sandstone_brick_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder(
                        "notcoppertools:block/brick_slab_side",
                        "notcoppertools:block/brick_slab_top",
                        "brick_stairs"
                )
        );
        //endregion
        //region slabs
        modelBuilders.add(
                new SlabModelBuilder("minecraft:block/oak_planks", "oak_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/conifer_planks", "conifer_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/birch_planks", "birch_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder("minecraft:block/cobblestone", "cobblestone_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder(
                        "minecraft:block/smooth_stone_slab_side",
                        "minecraft:block/smooth_stone",
                        "stone_slab"
                )
        );
        modelBuilders.add(
                new SlabModelBuilder(
                        "notcoppertools:block/sandstone_slab_side",
                        "notcoppertools:block/carved_sandstone",
                        "sandstone_slab"
                )
        );
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/stone_bricks", "stone_brick_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/sandstone_bricks", "sandstone_brick_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder(
                        "notcoppertools:block/brick_slab_side",
                        "notcoppertools:block/brick_slab_top",
                        "brick_slab"
                )
        );
        //endregion
        //region fences
        modelBuilders.add(
                new FenceModelBuilder(
                        "minecraft:block/oak_planks",
                        "oak_fence"
                )
        );
        modelBuilders.add(
                new FenceModelBuilder(
                        "notcoppertools:block/conifer_planks",
                        "conifer_fence"
                )
        );
        modelBuilders.add(
                new FenceModelBuilder(
                        "notcoppertools:block/birch_planks",
                        "birch_fence"
                )
        );
        //endregion
        //region gratings
        modelBuilders.add(
                new GratingModelBuilder(
                        "notcoppertools:block/iron_grating",
                        "notcoppertools:block/iron_grating_top",
                        "iron_grating"
                )
        );
        modelBuilders.add(
                new GratingModelBuilder(
                        "notcoppertools:block/gold_grating",
                        "notcoppertools:block/gold_grating_top",
                        "gold_grating"
                )
        );
        modelBuilders.add(
                new GratingModelBuilder(
                        "notcoppertools:block/copper_grating",
                        "notcoppertools:block/copper_grating_top",
                        "copper_grating"
                )
        );
        //endregion

        for (IJsonBuilder model : modelBuilders) {
            JsonFile[] files = model.getFiles();

            for (JsonFile file : files) {
                save(getTargetPath("assets", "models").resolve(file.name + ".json"), file.json);
            }
        }

    }

    protected static List<IJsonBuilder> blockstateBuilders = new ArrayList<>();
    protected static void populateBlockstates() {

        //region stairs
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/oak_stairs", "oak_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/conifer_stairs", "conifer_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/birch_stairs", "birch_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/cobblestone_stairs", "cobblestone_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/stone_stairs", "stone_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/sandstone_stairs", "sandstone_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/stone_brick_stairs", "stone_brick_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/sandstone_brick_stairs", "sandstone_brick_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/brick_stairs", "brick_stairs"));
        //endregion
        //region fences
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/oak_fence", "oak_fence"));
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/conifer_fence", "conifer_fence"));
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/birch_fence", "birch_fence"));
        //endregion

        for (IJsonBuilder blockstate : blockstateBuilders) {
            JsonFile[] files = blockstate.getFiles();

            for (JsonFile file : files) {
                save(getTargetPath("assets", "blockstates").resolve(file.name + ".json"), file.json);
            }
        }

    }

}
