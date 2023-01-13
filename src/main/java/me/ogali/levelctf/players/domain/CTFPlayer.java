package me.ogali.levelctf.players.domain;

import lombok.AllArgsConstructor;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class CTFPlayer {

    private final Player player;
    private final Team team;

}
