package me.ogali.levelctf.arenas.domain;

import lombok.Data;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Arena {

    private final String arenaId;
    private final int minAmountOfPlayers;
    private final int maxAmountOfPlayers;
    private List<Team> teamList;
    private List<Floor> floorList;

    public Arena(String arenaId, int minAmountOfPlayers, int maxAmountOfPlayers, int numberOfTeams, int numberOfFloors) {
        this.arenaId = arenaId;
        this.minAmountOfPlayers = minAmountOfPlayers;
        this.maxAmountOfPlayers = maxAmountOfPlayers;
        this.teamList = new ArrayList<>();
        this.floorList = new ArrayList<>();

        for (int i = 0; i < numberOfFloors; i++) {
            floorList.add(new Floor());
        }

        ChatColor[] teamColorList = {ChatColor.RED, ChatColor.GREEN, ChatColor.AQUA, ChatColor.YELLOW,
                ChatColor.GOLD, ChatColor.LIGHT_PURPLE, ChatColor.GRAY, ChatColor.WHITE, ChatColor.DARK_RED,
                ChatColor.DARK_GREEN, ChatColor.BLUE, ChatColor.DARK_GRAY, ChatColor.BLACK};
        for (int i = 0; i < numberOfTeams; i++) {
            teamList.add(new Team(teamColorList[i]));
        }
    }

}