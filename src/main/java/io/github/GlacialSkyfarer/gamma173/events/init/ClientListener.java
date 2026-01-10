package io.github.GlacialSkyfarer.gamma173.events.init;

import io.github.GlacialSkyfarer.gamma173.Gamma173;
import io.github.GlacialSkyfarer.gamma173.block.Blocks;
import io.github.GlacialSkyfarer.gamma173.block.GammaLeavesBlock;
import io.github.GlacialSkyfarer.gamma173.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.gamma173.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.gamma173.gui.AnvilScreen;
import io.github.GlacialSkyfarer.gamma173.gui.StonecutterScreen;
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
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;

@Environment(EnvType.CLIENT)
public class ClientListener {

    @EventListener
    public void registerScreenHandlers(GuiHandlerRegistryEvent event) {
        event.register(Gamma173.NAMESPACE.id("anvil"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openAnvil, AnvilBlockEntity::new));
        event.register(Gamma173.NAMESPACE.id("stonecutter"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openStonecutter, StonecutterBlockEntity::new));
    }

    private Screen openAnvil(PlayerEntity player, Inventory inventory) {
        return new AnvilScreen(player, (AnvilBlockEntity) inventory);
    }
    private Screen openStonecutter(PlayerEntity player, Inventory inventory) {
        return new StonecutterScreen(player, (StonecutterBlockEntity) inventory);
    }

    @EventListener
    public static void registerBlockColors(BlockColorsRegisterEvent event) {

        BlockColorProvider leavesBlockProvider = GammaLeavesBlock::getBlockColor;
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.OAK_LEAVES);
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.BIRCH_LEAVES);
        event.blockColors.registerColorProvider(leavesBlockProvider, Blocks.CONIFER_LEAVES);

    }

    @EventListener
    public static void registerItemColors(ItemColorsRegisterEvent event) {

        ItemColorProvider leavesItemProvider = GammaLeavesBlock::getItemColor;
        event.itemColors.register(leavesItemProvider, Blocks.OAK_LEAVES.asItem());
        event.itemColors.register(leavesItemProvider, Blocks.BIRCH_LEAVES.asItem());
        event.itemColors.register(leavesItemProvider, Blocks.CONIFER_LEAVES.asItem());

    }

    @EventListener
    public void registerBlockModelPredicates(BlockModelPredicateProviderRegistryEvent event) {
        event.registry.register(
                Blocks.OAK_LEAVES,
                Gamma173.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
        event.registry.register(
                Blocks.BIRCH_LEAVES,
                Gamma173.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
        event.registry.register(
                Blocks.CONIFER_LEAVES,
                Gamma173.NAMESPACE.id("graphics"),
                (blockState, blockView, blockPos, i) -> Minecraft.isFancyGraphicsEnabled() ? 1.0F : 0.0F
        );
    }

}
