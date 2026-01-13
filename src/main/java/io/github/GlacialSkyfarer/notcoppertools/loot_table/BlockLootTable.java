package io.github.GlacialSkyfarer.notcoppertools.loot_table;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockLootTable {

    public BlockLootTable() {
        this.entries = new ArrayList<>();
    }

    protected List<BlockLootEntry> entries;

    public BlockLootTable addEntry(BlockLootEntry entry) {
        this.entries.add(entry);
        return this;
    }

    public List<ItemStack> getDrops(Random random, World world, BlockState state, int x, int y, int z) {

        List<ItemStack> drops = new ArrayList<>();
        for (BlockLootEntry e : entries) {
            ItemStack i = e.getDrop(random, world, state, x,y,z);
            if (i != null) {
                drops.add(i);
            }
        }

        return drops;
    }

}
