package io.github.GlacialSkyfarer.notcoppertools.item;

import io.github.GlacialSkyfarer.notcoppertools.sound.SoundHelper;
import net.minecraft.block.Block;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class NCTMusicDiscItem extends TemplateItem {

    protected String sound;

    public NCTMusicDiscItem(Identifier identifier, String sound) {
        super(identifier);
        this.sound = sound;
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        if (world.getBlockId(x, y, z) == Block.JUKEBOX.id && world.getBlockMeta(x, y, z) == 0) {
            if (world.isRemote) {
                return true;
            } else {
                ((JukeboxBlock)Block.JUKEBOX).insertRecord(world, x, y, z, this.id);
                SoundHelper.playMusic(this.sound, x,y,z);
                --stack.count;
                return true;
            }
        } else {
            return false;
        }
    }
}
