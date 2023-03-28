package me.ogali.levelctf.teams.impl;

import me.ogali.levelctf.players.domain.CTFPlayer;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

import java.util.HashSet;
import java.util.Set;

public class MultiPlayerTeam extends Team {

    private final Set<CTFPlayer> teamMemberSet;

    public MultiPlayerTeam(ChatColor teamColor) {
        super(teamColor);
        this.teamMemberSet = new HashSet<>();
    }

    @Override
    public void teleportMembersToSpawn() {
        teamMemberSet.forEach(ctfPlayer -> ctfPlayer.getPlayer().teleport(getSpawnLocation()));
    }

}
