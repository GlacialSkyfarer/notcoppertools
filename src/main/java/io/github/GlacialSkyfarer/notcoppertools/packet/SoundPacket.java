package io.github.GlacialSkyfarer.notcoppertools.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SoundPacket extends Packet implements ManagedPacket<SoundPacket> {
    String sound;
    double x;
    double y;
    double z;
    float volume;
    float pitch;
    public SoundPacket() {}
    public SoundPacket(String sound, double x, double y, double z, float volume, float pitch) {
        this.sound = sound;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }
    public static final PacketType<SoundPacket> TYPE = PacketType.builder(true, true, SoundPacket::new).build();

    @Override
    public void read(DataInputStream stream) {
        try {
            sound = stream.readUTF();
            x = stream.readDouble();
            y = stream.readDouble();
            z = stream.readDouble();
            volume = stream.readFloat();
            pitch = stream.readFloat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(sound);
            stream.writeDouble(x);
            stream.writeDouble(y);
            stream.writeDouble(z);
            stream.writeFloat(volume);
            stream.writeFloat(pitch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER)) return;
        Minecraft.INSTANCE.world.playSound(x,y,z,sound,volume,pitch);
    }

    @Override
    public int size() {
        return 34 + sound.length();
    }

    @Override
    public @NotNull PacketType<SoundPacket> getType() {
        return TYPE;
    }
}
