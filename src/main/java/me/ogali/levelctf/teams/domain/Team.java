package me.ogali.levelctf.teams.domain;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Location;

@Data
public abstract class Team {

    private final ChatColor teamColor;
    private Location spawnLocation;

    public Team(ChatColor teamColor) {
        this.teamColor = teamColor;
    }

    public abstract void teleportMembersToSpawn();

    public String toStringColored() {
        return teamColor + teamColor.name().toUpperCase();
    }

    @Override
    public String toString() {
        return teamColor.name().toUpperCase();
    }

}
