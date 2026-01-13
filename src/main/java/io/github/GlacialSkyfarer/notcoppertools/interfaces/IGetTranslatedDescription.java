package io.github.GlacialSkyfarer.notcoppertools.interfaces;

import net.minecraft.client.resource.language.I18n;

public interface IGetTranslatedDescription {

    default String gamma_173$getTranslatedDescription() {
        return I18n.getTranslation("Oh, fiddlesticks. What now?");
    }

}
