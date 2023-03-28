package me.ogali.levelctf.arenas.domain;

import lombok.Data;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.teams.impl.MultiPlayerTeam;
import me.ogali.levelctf.worlds.MapCreator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

@Data
public class Arena {

    private final String id;
    private final MapCreator mapCreator;
    private final int minAmountOfPlayers;
    private final int maxAmountOfPlayers;
    private List<Team> teamList;
    private List<Floor> floorList;

    public Arena(String id, String mapSchematicName, int minAmountOfPlayers, int maxAmountOfPlayers, int numberOfTeams, int numberOfFloors) {
        this.id = id;
        this.mapCreator = new MapCreator(mapSchematicName);
        mapCreator.createNewMapInstance(true);
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
            teamList.add(new MultiPlayerTeam(teamColorList[i]));
        }
    }

    public Arena(Arena arena, World newArenaWorld) {
        this.id = arena.id;
        this.mapCreator = arena.mapCreator;
        this.minAmountOfPlayers = arena.minAmountOfPlayers;
        this.maxAmountOfPlayers = arena.maxAmountOfPlayers;
        this.teamList = new ArrayList<>();
        this.floorList = new ArrayList<>();
        arena.teamList
                .forEach(team -> {
                    Team newTeam = new MultiPlayerTeam(team.getTeamColor());
                    Location originalTeamSpawnLocationClone = team.getSpawnLocation().clone();
                    originalTeamSpawnLocationClone.setWorld(newArenaWorld);
                    newTeam.setSpawnLocation(originalTeamSpawnLocationClone);
                    teamList.add(newTeam);
                });
        arena.floorList
                .forEach(floor -> {
                    Floor newFloor = new Floor();
                    Location originalFloorSpawnLocationClone = floor.getSpawnLocation().clone();
                    originalFloorSpawnLocationClone.setWorld(newArenaWorld);
                    newFloor.setSpawnLocation(originalFloorSpawnLocationClone);
                    floorList.add(newFloor);
                });
    }

    public void initializeGame() {
        teamList.forEach(Team::teleportMembersToSpawn);
    }

}