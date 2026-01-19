package io.github.GlacialSkyfarer.notcoppertools.mixin.entity;

import io.github.GlacialSkyfarer.notcoppertools.item.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends LivingEntity {

    public CreeperEntityMixin(World world) {
        super(world);
    }

    @Inject(method="onKilledBy", at=@At(target = "Lnet/minecraft/entity/mob/CreeperEntity;dropItem(II)Lnet/minecraft/entity/ItemEntity;", value = "INVOKE"), cancellable = true)
    void overrideDrops(Entity par1, CallbackInfo ci) {
        int[] records = new int[] {
                Item.RECORD_THIRTEEN.id, Item.RECORD_CAT.id, Items.MUSIC_DISC_BLOCKS.id, Items.MUSIC_DISC_CHIRP.id,
                Items.MUSIC_DISC_FAR.id, Items.MUSIC_DISC_MALL.id, Items.MUSIC_DISC_MELLOHI.id, Items.MUSIC_DISC_STAL.id,
                Items.MUSIC_DISC_STRAD.id, Items.MUSIC_DISC_WARD.id, Items.MUSIC_DISC_11.id, Items.MUSIC_DISC_WAIT.id
        };
        dropItem(records[world.random.nextInt(records.length)], 1);
        ci.cancel();
    }
}
