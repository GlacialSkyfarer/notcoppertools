package io.github.GlacialSkyfarer.notcoppertools.mixin.item;

import com.llamalad7.mixinextras.lib.apache.commons.ArrayUtils;
import io.github.GlacialSkyfarer.notcoppertools.Util;
import io.github.GlacialSkyfarer.notcoppertools.interfaces.IGetTranslatedDescription;
import net.glasslauncher.mods.alwaysmoreitems.gui.AMITextRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements IGetTranslatedDescription, CustomTooltipProvider {

    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String gamma_173$getTranslatedDescription() {
        return I18n.getTranslation(getTranslationKey() + ".desc");
    }

    @Override
    public String[] getTooltip(ItemStack stack, String originalTooltip) {
        return ArrayUtils.addAll(
                new String[]{originalTooltip},
                Util.separateLines(((IGetTranslatedDescription)stack.getItem()).gamma_173$getTranslatedDescription(),
                        Formatting.GRAY + AMITextRenderer.ITALICS,
                        "")
        );
    }

}
