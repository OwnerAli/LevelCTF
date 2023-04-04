package me.ogali.levelctf.games.domain;

import lombok.Getter;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.games.Startable;
import me.ogali.levelctf.players.domain.CTFPlayer;
import me.ogali.levelctf.runnables.GameStartRunnable;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Getter
public class Game implements Startable {

    private final String id;
    private final Arena arena;
    private final World gameWorld;

    private final Set<CTFPlayer> playerSet;
    private final int rounds;

    public Game(String id, Arena arena, int rounds) {
        this.id = id;

        World newMapInstanceWorld = arena.getMapCreator().createNewMapInstance(false);
        this.arena = new Arena(arena, newMapInstanceWorld);
        this.gameWorld = newMapInstanceWorld;

        this.playerSet = new HashSet<>();
        this.rounds = rounds;
    }

    public void addPlayer(Player player) {
        playerSet.add(new CTFPlayer(player));
    }

    public boolean hasPlayer(Player player) {
        return getCtfPlayerByPlayer(player).isPresent();
    }

    public Optional<CTFPlayer> getCtfPlayerByPlayer(Player player) {
        return playerSet
                .stream()
                .filter(ctfPlayer -> ctfPlayer.getPlayer().equals(player))
                .findFirst();
    }

    public void startGameCountdown() {
        new GameStartRunnable(this, 5).startCountdown();
        addPlayer(Bukkit.getPlayer("xxAli"));
        addPlayer(Bukkit.getPlayer("VanixMc"));
    }

    public void register() {
        LevelCTF.getInstance().getGameRegistry().registerGame(this);
    }

    @Override
    public void start(Player player) {
        assignPlayersRandomTeams();
        arena.initializeGame();
        playerSet.forEach(ctfPlayer -> {
            ctfPlayer.setCurrentGame(this);
            ctfPlayer.teleportPlayerToTeamSpawn();
        });
    }

    private void assignPlayersRandomTeams() {
        Random random = LevelCTF.getInstance().getRandom();
        playerSet.forEach(ctfPlayer -> {
            Team randomTeam = arena.getTeamList().get(random.nextInt(arena.getTeamList().size()));
            ctfPlayer.setTeam(randomTeam);
        });
    }

}
