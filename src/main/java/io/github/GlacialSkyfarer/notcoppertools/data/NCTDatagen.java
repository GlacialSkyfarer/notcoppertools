package io.github.GlacialSkyfarer.notcoppertools.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.data.blockstate.builder.*;
import io.github.GlacialSkyfarer.notcoppertools.data.model.builder.*;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.FurnaceRecipeBuilder;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.ItemIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.TagIngredient;
import io.github.GlacialSkyfarer.notcoppertools.data.recipe.builder.*;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.alwaysmoreitems.plugins.vanilla.furnace.SmeltingRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.COPPER_TILES),
                        new ItemStack(Blocks.COPPER_TILE_STAIRS, 6),
                        "copper_tile_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_NETHERRACK),
                        new ItemStack(Blocks.NETHERRACK_STAIRS, 6),
                        "netherrack_stairs"
                )
        );
        recipeBuilders.add(
                new StairsRecipeBuilder(
                        ItemIngredient.of(Blocks.NETHERRACK_BRICKS),
                        new ItemStack(Blocks.NETHERRACK_BRICK_STAIRS, 6),
                        "netherrack_brick_stairs"
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
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.COPPER_TILES),
                        new ItemStack(Blocks.COPPER_TILE_SLAB, 6),
                        "copper_tile_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.CARVED_NETHERRACK),
                        new ItemStack(Blocks.NETHERRACK_SLAB, 6),
                        "netherrack_slab"
                )
        );
        recipeBuilders.add(
                new SlabRecipeBuilder(
                        ItemIngredient.of(Blocks.NETHERRACK_BRICKS),
                        new ItemStack(Blocks.NETHERRACK_BRICK_SLAB, 6),
                        "netherrack_brick_slab"
                )
        );
        //endregion
        //region ores
        recipeBuilders.add(
                new OreRecipeSetBuilder(Blocks.COPPER_ORE, Items.COPPER_INGOT, Blocks.COPPER_BLOCK, "copper")
        );
        //endregion
        //region trapdoors
        recipeBuilders.add(new TrapdoorRecipeBuilder(
                ItemIngredient.of(Block.PLANKS),
                new ItemStack(Blocks.OAK_TRAPDOOR, 2),
                "oak_trapdoor")
        );
        recipeBuilders.add(new TrapdoorRecipeBuilder(
                ItemIngredient.of(Blocks.CONIFER_PLANKS),
                new ItemStack(Blocks.CONIFER_TRAPDOOR, 2),
                "conifer_trapdoor")
        );
        recipeBuilders.add(new TrapdoorRecipeBuilder(
                ItemIngredient.of(Blocks.BIRCH_PLANKS),
                new ItemStack(Blocks.BIRCH_TRAPDOOR, 2),
                "birch_trapdoor")
        );
        recipeBuilders.add(new TrapdoorRecipeBuilder(
                ItemIngredient.of(Item.IRON_INGOT),
                new ItemStack(Blocks.IRON_TRAPDOOR, 2),
                "iron_trapdoor")
        );
        recipeBuilders.add(new TrapdoorRecipeBuilder(
                ItemIngredient.of(Items.COPPER_INGOT),
                new ItemStack(Blocks.COPPER_TRAPDOOR, 2),
                "copper_trapdoor")
        );
        //endregion
        //region doors
        recipeBuilders.add(
                new DoorRecipeBuilder(
                        ItemIngredient.of(Block.PLANKS),
                        Blocks.OAK_DOOR,
                        "oak_door"
                )
        );
        recipeBuilders.add(
                new DoorRecipeBuilder(
                        ItemIngredient.of(Item.IRON_INGOT),
                        Blocks.IRON_DOOR,
                        "iron_door"
                )
        );
        recipeBuilders.add(
                new DoorRecipeBuilder(
                        ItemIngredient.of(Items.COPPER_INGOT),
                        Blocks.COPPER_DOOR,
                        "copper_door"
                )
        );
        //endregion
        //region dye recipes
        recipeBuilders.add(new DyeRecipeBuilder(
                TagIngredient.of("notcoppertools:wool"),
                new Block[] {
                        Blocks.WHITE_WOOL,
                        Blocks.BLACK_WOOL,
                        Blocks.GRAY_WOOL,
                        Blocks.LIGHT_GRAY_WOOL,
                        Blocks.BROWN_WOOL,
                        Blocks.RED_WOOL,
                        Blocks.ORANGE_WOOL,
                        Blocks.YELLOW_WOOL,
                        Blocks.LIME_WOOL,
                        Blocks.GREEN_WOOL,
                        Blocks.CYAN_WOOL,
                        Blocks.LIGHT_BLUE_WOOL,
                        Blocks.BLUE_WOOL,
                        Blocks.PURPLE_WOOL,
                        Blocks.MAGENTA_WOOL,
                        Blocks.PINK_WOOL
                },
                DyeRecipeBuilder.RecipeType.SHAPELESS,
                "wool"));
        recipeBuilders.add(new DyeRecipeBuilder(
                TagIngredient.of("notcoppertools:stained_clay"),
                new Block[] {
                        Blocks.WHITE_STAINED_CLAY,
                        Blocks.BLACK_STAINED_CLAY,
                        Blocks.GRAY_STAINED_CLAY,
                        Blocks.LIGHT_GRAY_STAINED_CLAY,
                        Blocks.BROWN_STAINED_CLAY,
                        Blocks.RED_STAINED_CLAY,
                        Blocks.ORANGE_STAINED_CLAY,
                        Blocks.YELLOW_STAINED_CLAY,
                        Blocks.LIME_STAINED_CLAY,
                        Blocks.GREEN_STAINED_CLAY,
                        Blocks.CYAN_STAINED_CLAY,
                        Blocks.LIGHT_BLUE_STAINED_CLAY,
                        Blocks.BLUE_STAINED_CLAY,
                        Blocks.PURPLE_STAINED_CLAY,
                        Blocks.MAGENTA_STAINED_CLAY,
                        Blocks.PINK_STAINED_CLAY
                },
                DyeRecipeBuilder.RecipeType.EIGHT,
                "stained_clay"));
        //endregion

        recipeBuilders.add(new FurnaceRecipeBuilder()
                .setIngredient(ItemIngredient.of(Block.CLAY))
                .setResult(Blocks.HARDENED_CLAY)
                .setBaseName("hardened_clay"));

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
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/copper_tiles", "copper_tile_stairs")
        );
        modelBuilders.add(
                new StairsModelBuilder(
                        "notcoppertools:block/netherrack_slab_side",
                        "notcoppertools:block/carved_netherrack",
                        "netherrack_stairs"
                )
        );
        modelBuilders.add(
                new StairsModelBuilder("notcoppertools:block/netherrack_bricks", "netherrack_brick_stairs")
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
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/copper_tiles", "copper_tile_slab")
        );
        modelBuilders.add(
                new SlabModelBuilder(
                        "notcoppertools:block/netherrack_slab_side",
                        "notcoppertools:block/carved_netherrack",
                        "netherrack_slab"
                )
        );
        modelBuilders.add(
                new SlabModelBuilder("notcoppertools:block/netherrack_bricks", "netherrack_brick_slab")
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
        //region trapdoors
        modelBuilders.add(new TrapdoorModelBuilder("block/oak_trapdoor", "oak_trapdoor"));
        modelBuilders.add(new TrapdoorModelBuilder("notcoppertools:block/conifer_trapdoor", "conifer_trapdoor"));
        modelBuilders.add(new TrapdoorModelBuilder("notcoppertools:block/birch_trapdoor", "birch_trapdoor"));
        modelBuilders.add(new TrapdoorModelBuilder("notcoppertools:block/iron_trapdoor", "iron_trapdoor"));
        modelBuilders.add(new TrapdoorModelBuilder("notcoppertools:block/copper_trapdoor", "copper_trapdoor"));
        //endregion
        //region doors
        modelBuilders.add(new DoorModelBuilder("block/oak_door_bottom", "block/oak_door_top", "item/oak_door", "oak_door"));
        modelBuilders.add(new DoorModelBuilder("block/iron_door_bottom", "block/iron_door_top", "item/iron_door", "iron_door"));
        modelBuilders.add(new DoorModelBuilder("notcoppertools:block/copper_door_bottom", "notcoppertools:block/copper_door_top", "notcoppertools:item/copper_door", "copper_door"));
        //endregion

        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/hardened_clay", "hardened_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/white_stained_clay", "white_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/black_stained_clay", "black_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/gray_stained_clay", "gray_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/light_gray_stained_clay", "light_gray_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/brown_stained_clay", "brown_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/red_stained_clay", "red_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/orange_stained_clay", "orange_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/yellow_stained_clay", "yellow_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/lime_stained_clay", "lime_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/green_stained_clay", "green_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/cyan_stained_clay", "cyan_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/light_blue_stained_clay", "light_blue_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/blue_stained_clay", "blue_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/purple_stained_clay", "purple_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/magenta_stained_clay", "magenta_stained_clay"));
        modelBuilders.add(new BlockItemPairBuilder("notcoppertools:block/pink_stained_clay", "pink_stained_clay"));

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
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/copper_tile_stairs", "copper_tile_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/netherrack_stairs", "netherrack_stairs"));
        blockstateBuilders.add(new StairsBlockstateBuilder("notcoppertools:block/netherrack_brick_stairs", "netherrack_brick_stairs"));
        //endregion
        //region slabs
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/oak_slab", "oak_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/conifer_slab", "conifer_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/birch_slab", "birch_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/cobblestone_slab", "cobblestone_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/stone_slab", "stone_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/sandstone_slab", "sandstone_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/stone_brick_slab", "stone_brick_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/sandstone_brick_slab", "sandstone_brick_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/brick_slab", "brick_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/copper_tile_slab", "copper_tile_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/netherrack_slab", "netherrack_slab"));
        blockstateBuilders.add(new SlabBlockstateBuilder("notcoppertools:block/netherrack_brick_slab", "netherrack_brick_slab"));
        //endregion
        //region fences
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/oak_fence", "oak_fence"));
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/conifer_fence", "conifer_fence"));
        blockstateBuilders.add(new FenceBlockstateBuilder("notcoppertools:block/birch_fence", "birch_fence"));
        //endregion
        //region gratings
        blockstateBuilders.add(new GratingBlockstateBuilder("notcoppertools:block/iron_grating", "iron_grating"));
        blockstateBuilders.add(new GratingBlockstateBuilder("notcoppertools:block/gold_grating", "gold_grating"));
        blockstateBuilders.add(new GratingBlockstateBuilder("notcoppertools:block/copper_grating", "copper_grating"));
        //endregion
        //region trapdoors
        blockstateBuilders.add(new TrapdoorBlockstateBuilder("notcoppertools:block/oak_trapdoor", "oak_trapdoor"));
        blockstateBuilders.add(new TrapdoorBlockstateBuilder("notcoppertools:block/conifer_trapdoor", "conifer_trapdoor"));
        blockstateBuilders.add(new TrapdoorBlockstateBuilder("notcoppertools:block/birch_trapdoor", "birch_trapdoor"));
        blockstateBuilders.add(new TrapdoorBlockstateBuilder("notcoppertools:block/iron_trapdoor", "iron_trapdoor"));
        blockstateBuilders.add(new TrapdoorBlockstateBuilder("notcoppertools:block/copper_trapdoor", "copper_trapdoor"));
        //endregion
        //region doors
        blockstateBuilders.add(new DoorBlockstateBuilder("notcoppertools:block/oak_door", "oak_door"));
        blockstateBuilders.add(new DoorBlockstateBuilder("notcoppertools:block/iron_door", "iron_door"));
        blockstateBuilders.add(new DoorBlockstateBuilder("notcoppertools:block/copper_door", "copper_door"));
        //endregion

        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/hardened_clay", "hardened_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/white_stained_clay", "white_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/black_stained_clay", "black_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/gray_stained_clay", "gray_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/light_gray_stained_clay", "light_gray_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/brown_stained_clay", "brown_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/red_stained_clay", "red_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/orange_stained_clay", "orange_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/yellow_stained_clay", "yellow_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/lime_stained_clay", "lime_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/green_stained_clay", "green_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/cyan_stained_clay", "cyan_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/light_blue_stained_clay", "light_blue_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/blue_stained_clay", "blue_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/purple_stained_clay", "purple_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/magenta_stained_clay", "magenta_stained_clay"));
        blockstateBuilders.add(new GenericBlockBuilder("notcoppertools:block/pink_stained_clay", "pink_stained_clay"));

        for (IJsonBuilder blockstate : blockstateBuilders) {
            JsonFile[] files = blockstate.getFiles();

            for (JsonFile file : files) {
                save(getTargetPath("assets", "blockstates").resolve(file.name + ".json"), file.json);
            }
        }

    }

}
