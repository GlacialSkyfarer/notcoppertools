package io.github.GlacialSkyfarer.notcoppertools.gui.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class AnvilOutputSlot extends Slot {
    PlayerEntity player;
    Inventory inventory;

    public AnvilOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventory = inventory;
        this.player = player;
    }

    @Override
    public boolean canInsert(ItemStack stack) { return false; }

    @Override
    public void onTakeItem(ItemStack stack) {

        //DO CRAFTING STUFF IF ITEM IS CRAFTED
        //I DO NOT KNOW HOW TO FIND THAT OUT
        inventory.removeStack(0,1);
        inventory.removeStack(1,1);

        super.onTakeItem(stack);
    }
}
