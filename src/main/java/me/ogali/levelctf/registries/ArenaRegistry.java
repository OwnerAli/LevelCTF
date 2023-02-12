package me.ogali.levelctf.registries;

import me.ogali.levelctf.arenas.domain.Arena;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArenaRegistry {

    private final Map<String, Arena> arenaMap = new HashMap<>();

    /**
     * @param arena the arena to be registered
     */
    public void registerArena(Arena arena) {
        arenaMap.put(arena.getArenaId(), arena);
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
