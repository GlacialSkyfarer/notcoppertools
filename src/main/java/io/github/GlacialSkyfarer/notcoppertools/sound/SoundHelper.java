package io.github.GlacialSkyfarer.notcoppertools.sound;

import io.github.GlacialSkyfarer.notcoppertools.packet.SoundPacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

public abstract class SoundHelper {

    public static void playSound(String sound, double x, double y, double z, float volume, float pitch) {

        PacketHelper.send(new SoundPacket(sound,x,y,z,volume,pitch));
    }

}