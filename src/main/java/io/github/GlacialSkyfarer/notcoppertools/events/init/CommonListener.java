package io.github.GlacialSkyfarer.notcoppertools.events.init;

import com.jcraft.jogg.Packet;
import io.github.DanyGames2014.CraftingHelper;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.block.NCTLeavesBlock;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import io.github.GlacialSkyfarer.notcoppertools.packet.MusicPacket;
import io.github.GlacialSkyfarer.notcoppertools.packet.SoundPacket;
import io.github.GlacialSkyfarer.notcoppertools.recipe.StonecutterRecipeHandler;
import io.github.GlacialSkyfarer.notcoppertools.reef_biome.block.ReefBiomeBlocks;
import io.github.GlacialSkyfarer.notcoppertools.recipe.AnvilRecipeHandler;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.block.FireBurnableRegisterEvent;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.ItemConvertible;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;

import java.util.List;

import static io.github.GlacialSkyfarer.notcoppertools.NotCopperTools.NAMESPACE;
import static io.github.GlacialSkyfarer.notcoppertools.Util.setRepairMaterial;

public class CommonListener {

    @EventListener
    public static void serverInit(InitEvent event) {
        NotCopperTools.LOGGER.info("Successfully loaded {}", NAMESPACE);
    }

    @EventListener
    public static void registerBlocks(BlockRegistryEvent event) {
        Blocks.registerBlocks(event);
    }

    @EventListener
    public static void registerItems(ItemRegistryEvent event) {
        Items.registerItems(event);
    }

    @EventListener
    public static void afterBlockItemInit(AfterBlockAndItemRegisterEvent event) {

        //Vanilla repair materials

        setRepairMaterial(Item.WOODEN_SWORD, Block.PLANKS.asItem());
        setRepairMaterial(Item.WOODEN_PICKAXE, Block.PLANKS.asItem());
        setRepairMaterial(Item.WOODEN_AXE, Block.PLANKS.asItem());
        setRepairMaterial(Item.WOODEN_SHOVEL, Block.PLANKS.asItem());
        setRepairMaterial(Item.WOODEN_HOE, Block.PLANKS.asItem());

        setRepairMaterial(Item.LEATHER_HELMET, Item.LEATHER);
        setRepairMaterial(Item.LEATHER_CHESTPLATE, Item.LEATHER);
        setRepairMaterial(Item.LEATHER_LEGGINGS, Item.LEATHER);
        setRepairMaterial(Item.LEATHER_BOOTS, Item.LEATHER);

        setRepairMaterial(Item.STONE_SWORD, Block.COBBLESTONE.asItem());
        setRepairMaterial(Item.STONE_PICKAXE, Block.COBBLESTONE.asItem());
        setRepairMaterial(Item.STONE_AXE, Block.COBBLESTONE.asItem());
        setRepairMaterial(Item.STONE_SHOVEL, Block.COBBLESTONE.asItem());
        setRepairMaterial(Item.STONE_HOE, Block.COBBLESTONE.asItem());

        setRepairMaterial(Item.CHAIN_HELMET, Block.FIRE.asItem());
        setRepairMaterial(Item.CHAIN_CHESTPLATE, Block.FIRE.asItem());
        setRepairMaterial(Item.CHAIN_LEGGINGS, Block.FIRE.asItem());
        setRepairMaterial(Item.CHAIN_BOOTS, Block.FIRE.asItem());

        setRepairMaterial(Item.IRON_SWORD, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_PICKAXE, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_AXE, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_SHOVEL, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_HOE, Item.IRON_INGOT);

        setRepairMaterial(Item.IRON_HELMET, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_CHESTPLATE, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_LEGGINGS, Item.IRON_INGOT);
        setRepairMaterial(Item.IRON_BOOTS, Item.IRON_INGOT);

        setRepairMaterial(Item.GOLDEN_SWORD, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_PICKAXE, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_AXE, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_SHOVEL, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_HOE, Item.GOLD_INGOT);

        setRepairMaterial(Item.GOLDEN_HELMET, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_CHESTPLATE, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_LEGGINGS, Item.GOLD_INGOT);
        setRepairMaterial(Item.GOLDEN_BOOTS, Item.GOLD_INGOT);

        setRepairMaterial(Item.DIAMOND_SWORD, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_PICKAXE, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_AXE, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_SHOVEL, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_HOE, Item.DIAMOND);

        setRepairMaterial(Item.DIAMOND_HELMET, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_CHESTPLATE, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_LEGGINGS, Item.DIAMOND);
        setRepairMaterial(Item.DIAMOND_BOOTS, Item.DIAMOND);

        setRepairMaterial(Item.SHEARS, Item.IRON_INGOT);
        setRepairMaterial(Item.FISHING_ROD, Item.STRING);
        setRepairMaterial(Item.FLINT_AND_STEEL, Item.FLINT);

    }

    @EventListener
    public static void registerPackets(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, NAMESPACE.id("play_sound"), SoundPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, NAMESPACE.id("play_music"), MusicPacket.TYPE);
    }

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        AnvilRecipeHandler.registerRecipes();
        StonecutterRecipeHandler.registerRecipes();

        if (!NotCopperTools.CONFIG.removeVanillaRecipes) return;

        List<ItemConvertible> recipeRemovals = List.of(
                Item.WOODEN_DOOR,
                Block.TRAPDOOR,
                Block.WOOL,
                Block.COBBLESTONE_STAIRS,
                Block.WOODEN_STAIRS,
                Block.SLAB,
                Item.DYE,
                Block.FENCE,
                Item.IRON_DOOR
        );

        for (ItemConvertible recipeItem : recipeRemovals) {
            CraftingHelper.removeRecipe(recipeItem.asItem());
        }
    }

    @EventListener
    public static void afterItemSetup(AfterBlockAndItemRegisterEvent event) {
        Blocks.OAK_DOOR.asItem().setMaxCount(1);

        ((NCTLeavesBlock)Blocks.OAK_LEAVES).setSapling(Blocks.OAK_SAPLING.asItem());
        ((NCTLeavesBlock)Blocks.CONIFER_LEAVES).setSapling(Blocks.CONIFER_SAPLING.asItem());
        ((NCTLeavesBlock)Blocks.BIRCH_LEAVES).setSapling(Blocks.BIRCH_SAPLING.asItem());

        Item.RECORD_CAT.setTranslationKey("music_disc_cat");
        Item.RECORD_THIRTEEN.setTranslationKey("music_disc_13");
    }

    @EventListener
    public static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(AnvilBlockEntity.class, NAMESPACE.id("anvil").toString());
        event.register(StonecutterBlockEntity.class, NAMESPACE.id("stonecutter").toString());
    }

    @EventListener
    public static void registerFlammableBlocks(FireBurnableRegisterEvent event) {
        event.addBurnable(Blocks.CONIFER_PLANKS.id, 5, 20);
        event.addBurnable(Blocks.BIRCH_PLANKS.id, 5, 20);

        event.addBurnable(Blocks.OAK_SLAB.id, 5, 20);
        event.addBurnable(Blocks.CONIFER_SLAB.id, 5, 20);
        event.addBurnable(Blocks.BIRCH_SLAB.id, 5, 20);

        event.addBurnable(Blocks.OAK_STAIRS.id, 5, 20);
        event.addBurnable(Blocks.CONIFER_STAIRS.id, 5, 20);
        event.addBurnable(Blocks.BIRCH_STAIRS.id, 5, 20);

        event.addBurnable(Blocks.OAK_FENCE.id, 5, 20);
        event.addBurnable(Blocks.CONIFER_FENCE.id, 5, 20);
        event.addBurnable(Blocks.BIRCH_FENCE.id, 5, 20);

        event.addBurnable(Blocks.OAK_LOG.id, 5, 5);
        event.addBurnable(Blocks.CONIFER_LOG.id, 5, 5);
        event.addBurnable(Blocks.BIRCH_LOG.id, 5, 5);

        event.addBurnable(Blocks.OAK_LEAVES.id, 30, 60);
        event.addBurnable(Blocks.CONIFER_LEAVES.id, 30, 60);
        event.addBurnable(Blocks.BIRCH_LEAVES.id, 30, 60);

        event.addBurnable(Blocks.WHITE_WOOL.id, 30, 60);
        event.addBurnable(Blocks.BLACK_WOOL.id, 30, 60);
        event.addBurnable(Blocks.BROWN_WOOL.id, 30, 60);
        event.addBurnable(Blocks.GRAY_WOOL.id, 30, 60);
        event.addBurnable(Blocks.LIGHT_GRAY_WOOL.id, 30, 60);
        event.addBurnable(Blocks.RED_WOOL.id, 30, 60);
        event.addBurnable(Blocks.ORANGE_WOOL.id, 30, 60);
        event.addBurnable(Blocks.YELLOW_WOOL.id, 30, 60);
        event.addBurnable(Blocks.LIME_WOOL.id, 30, 60);
        event.addBurnable(Blocks.GREEN_WOOL.id, 30, 60);
        event.addBurnable(Blocks.CYAN_WOOL.id, 30, 60);
        event.addBurnable(Blocks.LIGHT_BLUE_WOOL.id, 30, 60);
        event.addBurnable(Blocks.BLUE_WOOL.id, 30, 60);
        event.addBurnable(Blocks.PURPLE_WOOL.id, 30, 60);
        event.addBurnable(Blocks.MAGENTA_WOOL.id, 30, 60);
        event.addBurnable(Blocks.PINK_WOOL.id, 30, 60);
    }

}
