package io.github.GlacialSkyfarer.notcoppertools.loot_table;

import com.google.gson.Gson;
import io.github.GlacialSkyfarer.notcoppertools.NotCopperTools;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.PreInitEvent;
import net.modificationstation.stationapi.api.resource.Filters;
import net.modificationstation.stationapi.api.resource.ResourceHelper;
import net.modificationstation.stationapi.api.util.Identifier;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;


public abstract class BlockLootTableRegistry {

    private static final HashMap<Identifier, BlockLootTable> registry = new HashMap<>();

    public static void addEntry(Identifier key, BlockLootTable value) {

        if (registry.containsKey(key)) return;
        registry.put(key, value);

    }

    public static BlockLootTable getEntry(Identifier key) {

        if (!registry.containsKey(key)) return null;
        return registry.get(key);

    }

    @EventListener
    public static void loadJsonTables(PreInitEvent event) {
        NotCopperTools.LOGGER.info("Searching for JSON block loot tables...");
        String tablePath = NotCopperTools.NAMESPACE + "/loot_tables/block";
        ResourceHelper.DATA.find(tablePath, Filters.FileType.JSON).forEach(BlockLootTableRegistry::registerTable);
    }

    protected static void registerTable(URL table)  {

        try {
            BlockLootTableJson json = new Gson().fromJson(new InputStreamReader(table.openStream()), BlockLootTableJson.class);
            addEntry(Identifier.tryParse(json.identifier), json.table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
