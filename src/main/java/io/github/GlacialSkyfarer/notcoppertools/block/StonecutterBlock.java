package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.StonecutterBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.container.StonecutterScreenHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class StonecutterBlock extends TemplateBlockWithEntity {

    public StonecutterBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new StonecutterBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.getBlockEntity(x,y,z) instanceof StonecutterBlockEntity stonecutter) {
            GuiHelper.openGUI(player, NotCopperTools.NAMESPACE.id("stonecutter"), stonecutter, new StonecutterScreenHandler(player, stonecutter));
            return true;
        }

        return false;
    }
}
