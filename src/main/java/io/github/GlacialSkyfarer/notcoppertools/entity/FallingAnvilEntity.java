package io.github.GlacialSkyfarer.notcoppertools.entity;

import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

//TODO: Actually use this
@HasTrackingParameters(trackingDistance = 64, updatePeriod = 1, sendVelocity = TriState.TRUE)
public class FallingAnvilEntity extends FallingBlockEntity implements MobSpawnDataProvider {
    public FallingAnvilEntity(World world) {
        super(world);
        setBoundingBoxSpacing(0.98f, 0.98f);

    }

    @Override
    public Identifier getHandlerIdentifier() {
        return NotCopperTools.NAMESPACE.id("falling_anvil");
    }
}
