package io.github.GlacialSkyfarer.notcoppertools.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ItemIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.IRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.OreRecipeSetBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.SlabRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.StairsRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NCTData {

    protected static List<IRecipeBuilder> recipeBuilders = new ArrayList<>();

    public static void dataGen() {
        boolean shouldRun = System.getProperty("runDatagen") != null;
        if (!shouldRun) return;
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
            NotCopperTools.LOGGER.error("Datagen shouldn't be running outside of a dev environment!");
            System.exit(1);
        }

        NotCopperTools.LOGGER.info("Beginning datagen for It's Not Copper Tools!");
        NotCopperTools.LOGGER.info("The game will close after datagen is complete.");

        populateRecipes();



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
        save(path, new GsonBuilder().setPrettyPrinting().create().toJson(object));
    }

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
        for (IRecipeBuilder recipe : recipeBuilders) {
            JsonFile[] files = recipe.getFiles();

            for (JsonFile file : files) {
                save(getTargetPath("data", "recipes").resolve(file.name + ".json"), file.json);
            }
        }
    }

}
