package io.github.GlacialSkyfarer.notcoppertools;

import io.github.GlacialSkyfarer.notcoppertools.interfaces.IHasRepairMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public abstract class Util {

    public static boolean isPlayerLookingAtTopHalfOfBlock(PlayerEntity player, int y) {

        return player.raycast(5.0, 1f).pos.y >= y + 0.5;

    }

    public static String[] separateLines(String string, String prefix, String suffix) {
        String[] result = string.split("\n");

        for (int i = 0; i < result.length; i++) {

            result[i] = prefix + result[i] + suffix;

        }

        return result;
    }

    public static void addPlayerInventory(ScreenHandler screenHandler, Inventory inventory, int xOffset, int yOffset) {
        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                screenHandler.addSlot(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
        //Hotbar
        for(int x = 0; x < 9; ++x) {
            screenHandler.addSlot(new Slot(inventory, x, 8 + x * 18, 142));
        }
    }

    public static void addPlayerInventory(ScreenHandler screenHandler, Inventory inventory) { addPlayerInventory(screenHandler, inventory, 0, 0); }

    public static Item setRepairMaterial(Item item, Item material) {
        if (item instanceof IHasRepairMaterial iCanHas) {
            return iCanHas.gamma_173$setRepairMaterial(material);
        }
        return item;
    }

}
