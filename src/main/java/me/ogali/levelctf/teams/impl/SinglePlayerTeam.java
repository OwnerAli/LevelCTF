package me.ogali.levelctf.teams.impl;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.players.domain.CTFPlayer;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

@Getter
@Setter
public class SinglePlayerTeam extends Team {

    private final CTFPlayer ctfPlayer;

    public SinglePlayerTeam(ChatColor teamColor, CTFPlayer ctfPlayer) {
        super(teamColor);
        this.ctfPlayer = ctfPlayer;
    }

    @Override
    public void teleportMembersToSpawn() {
        ctfPlayer.getPlayer().teleport(getSpawnLocation());
    }

}
