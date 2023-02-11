package me.ogali.levelctf.games.domain;

import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.games.Startable;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.entity.Player;

public class Game implements Startable {

    private final Arena arena;
    private final int rounds;

    public Game(Arena arena, int rounds) {
        this.arena = arena;
        this.rounds = rounds;
    }

    private void teleportPlayersToTeamSpawn() {
        arena.getTeamList().forEach(Team::teleportTeamMembersToSpawn);
    }

    @Override
    public void start(Player player) {
        teleportPlayersToTeamSpawn();
        arena.initializeGame(player);
    }

}
