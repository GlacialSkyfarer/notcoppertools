package io.github.GlacialSkyfarer.notcoppertools.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class NotCopperToolsConfig {
    @ConfigEntry(name = "Remove Vanilla Recipes", description = "Some recipes have had items such as wooden planks replaced to use tags or other items. Disable this to return these original recipes. (Requires restart.)")
    public Boolean removeVanillaRecipes = true;
}
