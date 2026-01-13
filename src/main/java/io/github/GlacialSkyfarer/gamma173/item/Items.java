package io.github.GlacialSkyfarer.gamma173.item;

import io.github.GlacialSkyfarer.gamma173.Gamma173;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.template.item.*;

import static io.github.GlacialSkyfarer.gamma173.Util.setRepairMaterial;

public class Items {

    public static Item COPPER_INGOT,

        ROSE_GOLD_SWORD,
        ROSE_GOLD_PICKAXE,
        ROSE_GOLD_AXE,
        ROSE_GOLD_SHOVEL,
        ROSE_GOLD_HOE,

        ROSE_GOLD_HELMET,
        ROSE_GOLD_CHESTPLATE,
        ROSE_GOLD_LEGGINGS,
        ROSE_GOLD_BOOTS,

        BONE_MEAL,
        INK_SAC,
        LAPIS_LAZULI,
        COCOA_BEANS,
        GRAY_DYE, LIGHT_GRAY_DYE, ROSE_RED, DANDELION_YELLOW, ORANGE_DYE,
        LIME_DYE, CACTUS_GREEN, CYAN_DYE, LIGHT_BLUE_DYE, PURPLE_DYE,
        MAGENTA_DYE, PINK_DYE,

        BAGWORM_SILK,
        SPRUCE_RESIN,
        APPLE_PIE;

    public static final ToolMaterial ROSE_GOLD_MATERIAL = ToolMaterialFactory.create(
            "rose_gold",
            2,
            761,
            7.0f,
            2
    ).toolLevel(ToolLevel.getNumeric(2));

    public static void registerItems(ItemRegistryEvent event) {

        COPPER_INGOT = new TemplateItem(Gamma173.NAMESPACE.id("copper_ingot"))
                .setTranslationKey(Gamma173.NAMESPACE.id("copper_ingot"));

        ROSE_GOLD_SWORD = new TemplateSwordItem(Gamma173.NAMESPACE.id("rose_gold_sword"), ROSE_GOLD_MATERIAL)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_sword"));
        ROSE_GOLD_PICKAXE = new TemplatePickaxeItem(Gamma173.NAMESPACE.id("rose_gold_pickaxe"), ROSE_GOLD_MATERIAL)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_pickaxe"));
        ROSE_GOLD_AXE = new TemplateAxeItem(Gamma173.NAMESPACE.id("rose_gold_axe"), ROSE_GOLD_MATERIAL)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_axe"));
        ROSE_GOLD_SHOVEL = new TemplateShovelItem(Gamma173.NAMESPACE.id("rose_gold_shovel"), ROSE_GOLD_MATERIAL)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_shovel"));
        ROSE_GOLD_HOE = new TemplateHoeItem(Gamma173.NAMESPACE.id("rose_gold_hoe"), ROSE_GOLD_MATERIAL)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_hoe"));

        setRepairMaterial(ROSE_GOLD_SWORD, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_PICKAXE, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_AXE, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_SHOVEL, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_HOE, Item.GOLD_INGOT);

        ROSE_GOLD_HELMET = new GammaArmorItem(Gamma173.NAMESPACE.id("rose_gold_helmet"), 20, 0, "rose_gold")
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_helmet"));
        ROSE_GOLD_CHESTPLATE = new GammaArmorItem(Gamma173.NAMESPACE.id("rose_gold_chestplate"), 20, 1, "rose_gold")
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_chestplate"));
        ROSE_GOLD_LEGGINGS = new GammaArmorItem(Gamma173.NAMESPACE.id("rose_gold_leggings"), 20, 2, "rose_gold")
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_leggings"));
        ROSE_GOLD_BOOTS = new GammaArmorItem(Gamma173.NAMESPACE.id("rose_gold_boots"), 20, 3, "rose_gold")
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_gold_boots"));

        setRepairMaterial(ROSE_GOLD_HELMET, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_CHESTPLATE, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_LEGGINGS, Item.GOLD_INGOT);
        setRepairMaterial(ROSE_GOLD_BOOTS, Item.GOLD_INGOT);

        BONE_MEAL = new GammaBonemealItem(Gamma173.NAMESPACE.id("bone_meal"), 15)
                .setTranslationKey(Gamma173.NAMESPACE.id("bone_meal"));
        INK_SAC = new GammaDyeItem(Gamma173.NAMESPACE.id("ink_sac"), 0)
                .setTranslationKey(Gamma173.NAMESPACE.id("ink_sac"));
        LAPIS_LAZULI = new GammaDyeItem(Gamma173.NAMESPACE.id("lapis_lazuli"), 4)
                .setTranslationKey(Gamma173.NAMESPACE.id("lapis_lazuli"));
        COCOA_BEANS = new GammaDyeItem(Gamma173.NAMESPACE.id("cocoa_beans"), 3)
                .setTranslationKey(Gamma173.NAMESPACE.id("cocoa_beans"));
        GRAY_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("gray_dye"), 8)
                .setTranslationKey(Gamma173.NAMESPACE.id("gray_dye"));
        LIGHT_GRAY_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("light_gray_dye"), 7)
                .setTranslationKey(Gamma173.NAMESPACE.id("light_gray_dye"));
        ROSE_RED = new GammaDyeItem(Gamma173.NAMESPACE.id("rose_red"), 1)
                .setTranslationKey(Gamma173.NAMESPACE.id("rose_red"));
        ORANGE_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("orange_dye"), 14)
                .setTranslationKey(Gamma173.NAMESPACE.id("orange_dye"));
        DANDELION_YELLOW = new GammaDyeItem(Gamma173.NAMESPACE.id("dandelion_yellow"), 11)
                .setTranslationKey(Gamma173.NAMESPACE.id("dandelion_yellow"));
        LIME_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("lime_dye"), 10)
                .setTranslationKey(Gamma173.NAMESPACE.id("lime_dye"));
        CACTUS_GREEN = new GammaDyeItem(Gamma173.NAMESPACE.id("cactus_green"), 2)
                .setTranslationKey(Gamma173.NAMESPACE.id("cactus_green"));
        CYAN_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("cyan_dye"), 6)
                .setTranslationKey(Gamma173.NAMESPACE.id("cyan_dye"));
        LIGHT_BLUE_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("light_blue_dye"), 12)
                .setTranslationKey(Gamma173.NAMESPACE.id("light_blue_dye"));
        PURPLE_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("purple_dye"), 5)
                .setTranslationKey(Gamma173.NAMESPACE.id("purple_dye"));
        MAGENTA_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("magenta_dye"), 13)
                .setTranslationKey(Gamma173.NAMESPACE.id("magenta_dye"));
        PINK_DYE = new GammaDyeItem(Gamma173.NAMESPACE.id("pink_dye"), 9)
                .setTranslationKey(Gamma173.NAMESPACE.id("pink_dye"));

        SPRUCE_RESIN = new TemplateItem(Gamma173.NAMESPACE.id("spruce_resin"))
                .setTranslationKey(Gamma173.NAMESPACE.id("spruce_resin"));
        BAGWORM_SILK = new TemplateItem(Gamma173.NAMESPACE.id("bagworm_silk"))
                .setTranslationKey(Gamma173.NAMESPACE.id("bagworm_silk"));
        APPLE_PIE = new ApplePieItem(Gamma173.NAMESPACE.id("apple_pie"))
                .setTranslationKey(Gamma173.NAMESPACE.id("apple_pie"));

    }

}
