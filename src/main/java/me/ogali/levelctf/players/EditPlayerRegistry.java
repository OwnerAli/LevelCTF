package me.ogali.levelctf.players;

import me.ogali.levelctf.players.domain.EditPlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EditPlayerRegistry {

    private final Set<EditPlayer> editPlayerSet = new HashSet<>();

    public void addEditPlayer(EditPlayer editPlayer) {
        editPlayerSet.add(editPlayer);
    }

    public void removeEditPlayer(EditPlayer editPlayer) {
        editPlayerSet.remove(editPlayer);
    }

    public Optional<EditPlayer> getEditPlayer(Player player) {
        return editPlayerSet.stream()
                .filter(adminPlayer -> adminPlayer.getPlayer().equals(player))
                .findFirst();
    }

}
