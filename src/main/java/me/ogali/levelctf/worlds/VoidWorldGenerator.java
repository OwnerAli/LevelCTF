package me.ogali.levelctf.worlds;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class VoidWorldGenerator extends WorldCreator {

    public VoidWorldGenerator(String name) {
        super(name);
        type(WorldType.FLAT);
        generatorSettings("{\"layers\": [{\"block\": \"air\", \"height\": 100}], \"biome\":\"plains\"}");
        generateStructures(false);
    }

}
