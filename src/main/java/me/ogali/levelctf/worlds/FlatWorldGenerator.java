package me.ogali.levelctf.worlds;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class FlatWorldGenerator extends WorldCreator {

    public FlatWorldGenerator(String name) {
        super(name);
        type(WorldType.FLAT);
        generatorSettings("{\"layers\": [{\"block\": \"air\", \"height\": 100}], \"biome\":\"plains\"}");
        generateStructures(false);
    }

}
