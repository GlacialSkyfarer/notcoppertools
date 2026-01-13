package io.github.GlacialSkyfarer.notcoppertools.block;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.block.entity.AnvilBlockEntity;
import io.github.GlacialSkyfarer.notcoppertools.gui.container.AnvilScreenHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class AnvilBlock extends TemplateBlockWithEntity {

    public AnvilBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new AnvilBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.getBlockEntity(x,y,z) instanceof AnvilBlockEntity anvil) {
            GuiHelper.openGUI(player, NotCopperTools.NAMESPACE.id("anvil"), anvil, new AnvilScreenHandler(player, anvil));

            return true;
        }

        return false;
    }
}
