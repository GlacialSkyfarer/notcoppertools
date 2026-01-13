package io.github.GlacialSkyfarer.notcoppertools.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.model.item.ItemModelPredicateProvider;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ApplePieItem extends TemplateFoodItem {
    public ApplePieItem(Identifier identifier) {
        super(identifier, 7, false);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if (stack.getStationNbt().getBoolean("half")) {
            stack.count--;
        } else {
            NbtCompound nbt = stack.getStationNbt();
            nbt.putBoolean("half", true);
        }
        user.heal(this.getHealthRestored());
        return stack;
    }
}