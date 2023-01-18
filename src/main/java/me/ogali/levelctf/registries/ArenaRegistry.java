package me.ogali.levelctf.registries;

import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArenaRegistry {

    private final Map<String, Arena> arenaMap = new HashMap<>();

    /**
     * @param arena the arena to be registered
     * @return true if the arena was successfully created,
     * false if an arena of the same id is already present.
     */
    public boolean registerArena(Arena arena) {
        if (arenaMap.get(arena.getArenaId()) != null) return false;
        arenaMap.put(arena.getArenaId(), arena);
        return true;
    }

    /**
     * @param arenaId a unique id to identify each arena
     * @return An optional of either null or the arena object
     */
    public Optional<Arena> getArenaById(String arenaId) {
        return Optional.ofNullable(arenaMap.get(arenaId));
    }

    /**
     * @param arenaId a unique id to identify each arena
     * @return true if there is an arena with specified ID, otherwise false
     */
    public boolean isRegisteredArena(String arenaId) {
        return arenaMap.containsKey(arenaId);
    }

}
