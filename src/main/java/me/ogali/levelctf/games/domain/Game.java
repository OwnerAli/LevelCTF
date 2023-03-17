package me.ogali.levelctf.games.domain;

import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.games.Startable;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Game implements Startable {

    private final Arena arena;
    private final int rounds;

    public Game(Arena arena, int rounds) {
        this.arena = arena;
        this.rounds = rounds;
    }

    @Override
    public void start(Player player) {
        World newMapInstanceWorld = arena.getMapCreator().createNewMapInstance(false);
        arena.initializeGame(player, newMapInstanceWorld);
    }

}
