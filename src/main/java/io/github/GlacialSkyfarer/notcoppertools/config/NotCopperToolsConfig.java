package io.github.GlacialSkyfarer.notcoppertools.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class NotCopperToolsConfig {
    @ConfigEntry(name = "Remove Vanilla Recipes", description = "Some recipes have had items such as wooden planks replaced to use tags or other items. Disable this to return these original recipes. (Requires restart.)")
    public Boolean removeVanillaRecipes = true;
    @ConfigEntry(name = "Convert Blocks", description = "Turn this off if you're making a brand new world. This converts all vanilla blocks to their NCT counterparts.")
    public Boolean convertBlocks = true;
    @ConfigEntry(name = "Convert Items", description = "Turn this off if you're making a brand new world. This converts all vanilla items to their NCT counterparts.")
    public Boolean convertItems = true;
}
