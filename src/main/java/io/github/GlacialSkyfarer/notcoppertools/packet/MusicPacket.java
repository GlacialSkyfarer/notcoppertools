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

public class MusicPacket extends Packet implements ManagedPacket<MusicPacket> {
    String music;
    int x;
    int y;
    int z;
    public MusicPacket() {}
    public MusicPacket(String music, int x, int y, int z) {
        this.music = music;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static final PacketType<MusicPacket> TYPE = PacketType.builder(true, true, MusicPacket::new).build();

    @Override
    public void read(DataInputStream stream) {
        try {
            music = stream.readUTF();
            x = stream.readInt();
            y = stream.readInt();
            z = stream.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeUTF(music);
            stream.writeInt(x);
            stream.writeInt(y);
            stream.writeInt(z);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER)) return;
        if(music.isEmpty()) {
            music = null;
        }
        Minecraft.INSTANCE.world.playStreaming(music, x,y,z);
    }

    @Override
    public int size() {
        return 12 + music.length();
    }

    @Override
    public @NotNull PacketType<MusicPacket> getType() {
        return TYPE;
    }
}
