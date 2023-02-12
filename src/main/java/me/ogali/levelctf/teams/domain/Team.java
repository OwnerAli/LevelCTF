package me.ogali.levelctf.teams.domain;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Team {

    private final List<UUID> teamMembersList;
    private final ChatColor teamColor;
    private Location spawnLocation;

    public Team(ChatColor teamColor) {
        this.teamMembersList = new ArrayList<>();
        this.teamColor = teamColor;
    }

    public void teleportTeamMembersToSpawn() {
        teamMembersList.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) return;

            player.teleport(spawnLocation);
        });
    }

    public String toStringColored() {
        return teamColor + teamColor.name().toUpperCase();
    }

    @Override
    public String toString() {
        return teamColor.name().toUpperCase();
    }

}
