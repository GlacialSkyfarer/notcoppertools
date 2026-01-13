package io.github.GlacialSkyfarer.notcoppertools.gui.container;

import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.slot.AnvilOutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AnvilScreenHandler extends ScreenHandler {

    private final AnvilBlockEntity anvil;

    public AnvilScreenHandler(PlayerEntity player, AnvilBlockEntity anvil) {

        this.anvil = anvil;

        this.addSlot(new Slot(anvil, 0, 56, 17));
        this.addSlot(new Slot(anvil, 1, 56, 53));
        this.addSlot(new AnvilOutputSlot(player, anvil, 2, 116, 35));

        Util.addPlayerInventory(this, player.inventory);

    }

    @Override
    public void onClosed(PlayerEntity player) {
        if (anvil.getStack(0) != null) player.dropItem(anvil.getStack(0));
        if (anvil.getStack(1) != null) player.dropItem(anvil.getStack(1));
        anvil.clearStacks();
        super.onClosed(player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return anvil.canPlayerUse(player);
    }

}
