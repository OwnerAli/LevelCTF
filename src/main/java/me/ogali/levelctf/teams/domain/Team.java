package me.ogali.levelctf.teams.domain;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Location;

@Data
public class Team {

    private final ChatColor teamColor;
    private Location spawnLocation;

    @Override
    public String toString() {
        return teamColor.name().toUpperCase();
    }

}
