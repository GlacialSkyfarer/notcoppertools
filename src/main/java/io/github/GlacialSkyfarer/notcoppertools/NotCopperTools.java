package io.github.GlacialSkyfarer.notcoppertools;

import io.github.GlacialSkyfarer.notcoppertools.config.NotCopperToolsConfig;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

public class NotCopperTools {

    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger();

    @ConfigRoot(value="config", visibleName = "It's Not Copper Tools")
    public static final NotCopperToolsConfig CONFIG = new NotCopperToolsConfig();

}
