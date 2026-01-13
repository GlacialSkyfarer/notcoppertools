package io.github.GlacialSkyfarer.notcoppertools.loot_table;

import io.github.GlacialSkyfarer.notcoppertools.loot_table.predicates.IBlockLootPredicate;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockLootEntry {

    protected String itemId;
    protected int minCount;
    protected int maxCount;
    protected int minDamage;
    protected int maxDamage;
    protected List<IBlockLootPredicate> predicates;

    public BlockLootEntry(String id, int minC, int maxC, int minD, int maxD) {
        itemId = id;
        minCount = minC;
        maxCount = maxC;
        minDamage = minD;
        maxDamage = maxD;
        predicates = new ArrayList<>();
    }
    public BlockLootEntry(String id, int minC, int maxC) {
        this(id, minC, maxC, 0, 0);
    }
    public BlockLootEntry(String id) {
        this(id,1,1,0,0);
    }

    public BlockLootEntry addPredicate(IBlockLootPredicate predicate) {

        this.predicates.add(predicate);
        return this;

    }

    public ItemStack getDrop(Random random, World world, BlockState state, int x, int y, int z) {
        for(IBlockLootPredicate p : predicates) {
            if (!p.isFulfilled(state, random)) return null;
        }
        int count = minCount >= maxCount ? minCount : random.nextInt(minCount, maxCount);
        int damage = minDamage >= maxDamage ? minDamage : random.nextInt(minDamage, maxDamage);
        Identifier identifier = Identifier.tryParse(itemId);
        return new ItemStack(ItemRegistry.INSTANCE.get(identifier), count, damage);
    }

}
