package io.github.GlacialSkyfarer.gamma173.item;

import io.github.GlacialSkyfarer.gamma173.interfaces.IDyeableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.bonemeal.BonemealAPI;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class GammaBonemealItem extends GammaDyeItem {

    public GammaBonemealItem(Identifier identifier, int color) {
        super(identifier, color);
    }

    @Override
    public boolean useOnBlock(ItemStack item, PlayerEntity player, World world, int x, int y, int z, int side) {
        BlockState state = world.getBlockState(x, y, z);
        if (state.getBlock().onBonemealUse(world, x, y, z, state)) {
            world.setBlocksDirty(x, y, z, x, y, z);
            item.count--;
            return true;
        }
        if (BonemealAPI.generate(world, x, y, z, state, side)) {
            world.setBlocksDirty(x - 8, y - 8, z - 8, x + 8, y + 8, z + 8);
            item.count--;
            return true;
        }
        return false;
    }
}
