package me.ogali.levelctf.teams.impl;

import me.ogali.levelctf.players.domain.CTFPlayer;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.Set;

public class MultiPlayerTeam extends Team {

    private final Set<CTFPlayer> ctfPlayerSet;

    public MultiPlayerTeam(ChatColor teamColor, Set<CTFPlayer> ctfPlayerSet) {
        super(teamColor);
        this.ctfPlayerSet = ctfPlayerSet;
    }

}
