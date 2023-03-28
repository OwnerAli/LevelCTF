package me.ogali.levelctf.games;

import me.ogali.levelctf.games.domain.Game;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameRegistry {

    private final Set<Game> activeGameSet = new HashSet<>();

    public void registerGame(Game game) {
        activeGameSet.add(game);
    }

    public Optional<Game> getGameById(String id) {
        return activeGameSet
                .stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }

    public Optional<Game> getGameByPlayer(Player player) {
        return activeGameSet
                .stream()
                .filter(game -> game.hasPlayer(player))
                .findFirst();
    }

    public Collection<String> getActiveGameIds() {
        return activeGameSet
                .stream()
                .map(Game::getId)
                .collect(Collectors.toList());
    }

}
