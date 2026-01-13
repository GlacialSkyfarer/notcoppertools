package io.github.GlacialSkyfarer.notcoppertools.gui;

import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.container.AnvilScreenHandler;
import io.github.GlacialSkyfarer.notcoppertools.gui.container.StonecutterScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class StonecutterScreen extends HandledScreen {

    public StonecutterScreen(PlayerEntity player, StonecutterBlockEntity stonecutter) {
        super(new StonecutterScreenHandler(player, stonecutter));
        this.backgroundHeight = 165;
    }

    @Override
    protected void drawForeground() {
        this.textRenderer.draw("Stonecutter", 10, 6, 4210752);
        this.textRenderer.draw("Inventory", 8, this.backgroundHeight - 96 + 2, 4210752);
    }

    @Override
    protected void drawBackground(float tickDelta) {

        int backgroundId = this.minecraft.textureManager.getTextureId("/assets/notcoppertools/stationapi/textures/gui/stonecutter.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(backgroundId);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

    }
}
