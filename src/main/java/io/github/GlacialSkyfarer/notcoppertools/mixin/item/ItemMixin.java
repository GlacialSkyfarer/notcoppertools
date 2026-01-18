package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import com.llamalad7.mixinextras.lib.apache.commons.ArrayUtils;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.datafixer.NCTDatafixer;
import io.github.GlacialSkyfarer.notcoppertools.interfaces.IGetTranslatedDescription;
import net.glasslauncher.mods.alwaysmoreitems.gui.AMITextRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin implements IGetTranslatedDescription, CustomTooltipProvider {

    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String notcoppertools$getTranslatedDescription() {
        return I18n.getTranslation(getTranslationKey() + ".desc");
    }

    @Override
    public String[] getTooltip(ItemStack stack, String originalTooltip) {
        return ArrayUtils.addAll(
                new String[]{originalTooltip},
                Util.separateLines(((IGetTranslatedDescription)stack.getItem()).notcoppertools$getTranslatedDescription(),
                        Formatting.GRAY + AMITextRenderer.ITALICS,
                        "")
        );
    }

    @Inject(method="inventoryTick", at=@At("RETURN"))
    private void convertItems(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (NotCopperTools.CONFIG.convertItems) {
            int itemId = stack.itemId;
            stack.itemId = NCTDatafixer.fixItemStack(stack);
            if (stack.itemId != itemId) stack.setDamage(0);
        }
    }

}
