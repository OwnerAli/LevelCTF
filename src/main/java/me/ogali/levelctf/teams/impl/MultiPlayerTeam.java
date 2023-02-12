package me.ogali.levelctf.teams.impl;

import me.ogali.levelctf.players.domain.CTFPlayer;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

import java.util.Set;

public class MultiPlayerTeam extends Team {

    private final Set<CTFPlayer> teamMemberSet;

    public MultiPlayerTeam(ChatColor teamColor, Set<CTFPlayer> teamMemberSet) {
        super(teamColor);
        this.teamMemberSet = teamMemberSet;
    }

}
