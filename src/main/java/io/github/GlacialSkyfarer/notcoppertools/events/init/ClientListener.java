package io.github.GlacialSkyfarer.notcoppertools.events.init;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.Blocks;
import io.github.GlacialSkyfarer.notcoppertools.block.NCTLeavesBlock;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.data.NCTData;
import io.github.GlacialSkyfarer.notcoppertools.gui.AnvilScreen;
import io.github.GlacialSkyfarer.notcoppertools.gui.StonecutterScreen;
import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.color.block.BlockColorProvider;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.client.event.color.block.BlockColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.model.BlockModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.client.event.render.model.ItemModelPredicateProviderRegistryEvent;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;

@Environment(EnvType.CLIENT)
public class ClientListener {

    @EventListener
    public void registerScreenHandlers(GuiHandlerRegistryEvent event) {
        event.register(NotCopperTools.NAMESPACE.id("anvil"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openAnvil, AnvilBlockEntity::new));
        event.register(NotCopperTools.NAMESPACE.id("stonecutter"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openStonecutter, StonecutterBlockEntity::new));
    }

    private Screen openAnvil(PlayerEntity player, Inventory inventory) {
        return new AnvilScreen(player, (AnvilBlockEntity) inventory);
    }
    private Screen openStonecutter(PlayerEntity player, Inventory inventory) {
        return new StonecutterScreen(player, (StonecutterBlockEntity) inventory);
    }

    @EventListener
    public static void registerBlockColors(BlockColorsRegisterEvent event) {

        BlockColorProvider leavesBlockProvider = NCTLeavesBlock::getBlockColor;
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.OAK_LEAVES);
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.BIRCH_LEAVES);
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.CONIFER_LEAVES);

    }

    @EventListener
    public static void registerItemModelPredicates(ItemModelPredicateProviderRegistryEvent event) {

        event.register(Items.APPLE_PIE.id, NotCopperTools.NAMESPACE.id("half"), (itemStack, clientWorld, livingEntity, seed) -> itemStack.getStationNbt().getBoolean("half") ? 1.0F : 0.0F);

    }

    @EventListener
    public static void registerItemColors(ItemColorsRegisterEvent event) {

        ItemColorProvider leavesItemProvider = NCTLeavesBlock::getItemColor;
        event.itemColors.register(leavesItemProvider, Blocks.OAK_LEAVES.asItem());
        event.itemColors.register(leavesItemProvider, Blocks.BIRCH_LEAVES.asItem());
        event.itemColors.register(leavesItemProvider, Blocks.CONIFER_LEAVES.asItem());

    }

    @EventListener
    public void registerBlockModelPredicates(BlockModelPredicateProviderRegistryEvent event) {
        event.registry.register(
                Blocks.OAK_LEAVES,
                NotCopperTools.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
        event.registry.register(
                Blocks.BIRCH_LEAVES,
                NotCopperTools.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
        event.registry.register(
                Blocks.CONIFER_LEAVES,
                NotCopperTools.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
    }

    @EventListener
    public void runDatagen(AfterBlockAndItemRegisterEvent event) {
        NCTData.dataGen();
    }

}
