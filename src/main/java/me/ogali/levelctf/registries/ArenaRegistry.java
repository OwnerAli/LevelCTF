package me.ogali.levelctf.registries;

import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class ArenaRegistry {

    private final Map<String, Arena> arenaMap = new HashMap<>();

    /**
     *
     * @param arenaId a unique id to identify each arena
     * @return true if there is no arena with specified id, otherwise false
     *
     */
    public boolean registerArena(String arenaId) {
        if (isRegisteredArena(arenaId)) return false;

        Arena arena = new Arena(arenaId);
        arenaMap.put(arenaId, arena);
        return true;
    }

    public Arena getArenaById(String arenaId) {
        return arenaMap.get(arenaId);
    }

    /**
     * @param arenaId a unique id to identify each arena
     * @return true if there is an arena with specified ID, otherwise false
     */
    public boolean isRegisteredArena(String arenaId) {
        return arenaMap.containsKey(arenaId);
    }

}
