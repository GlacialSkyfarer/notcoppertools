package io.github.GlacialSkyfarer.gamma173;

import io.github.GlacialSkyfarer.gamma173.config.Gamma173Config;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

public class Gamma173 {

    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger();

    @ConfigRoot(value="config", visibleName = "It's Not Copper Tools")
    public static final Gamma173Config CONFIG = new Gamma173Config();

}
