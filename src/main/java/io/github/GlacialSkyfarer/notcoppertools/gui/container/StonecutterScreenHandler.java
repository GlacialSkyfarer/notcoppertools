package io.github.GlacialSkyfarer.notcoppertools.gui.container;

import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.slot.AnvilOutputSlot;
import io.github.GlacialSkyfarer.notcoppertools.gui.slot.StonecutterOutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class StonecutterScreenHandler extends ScreenHandler {

    private final StonecutterBlockEntity stonecutter;

    public StonecutterScreenHandler(PlayerEntity player, StonecutterBlockEntity stonecutter) {

        this.stonecutter = stonecutter;

        this.addSlot(new Slot(stonecutter, 0, 21, 35));
        int i = 1;
        for(int y = 0; y < 4; ++y) {
            for(int x = 0; x < 5; ++x) {
                this.addSlot(new StonecutterOutputSlot(player, stonecutter, i, 80 + x * 18, 8 + y * 18));
                i++;
            }
        }
        Util.addPlayerInventory(this, player.inventory);

    }

    @Override
    public void onClosed(PlayerEntity player) {
        if (stonecutter.getStack(0) != null) player.dropItem(stonecutter.getStack(0));
        stonecutter.clearStacks();
        super.onClosed(player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return stonecutter.canPlayerUse(player);
    }

}
