package me.ogali.levelctf.registries;

import me.ogali.levelctf.players.domain.EditPlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EditPlayerRegistry {

    private final Set<EditPlayer> adminPlayerSet = new HashSet<>();

    public void addEditPlayer(EditPlayer editPlayer) {
        adminPlayerSet.add(editPlayer);
    }

    public void removeEditPlayer(EditPlayer editPlayer) {
        adminPlayerSet.remove(editPlayer);
    }

    public Optional<EditPlayer> getEditPlayer(Player player) {
        return adminPlayerSet.stream()
                .filter(adminPlayer -> adminPlayer.getPlayer().equals(player))
                .findFirst();
    }


}
