package me.ogali.levelctf.arenas.domain;

import lombok.Data;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
public class Arena {

    private final String arenaId;
    private final List<Floor> floorList;
    private final List<Team> teamList;
    private final List<Loot<ItemStack>> lootTable;

    public Arena(String arenaId) {
        this.arenaId = arenaId;
        this.floorList = new ArrayList<>();
        this.teamList = new ArrayList<>();
        this.lootTable = new ArrayList<>();
    }

    public void initializeGame(Player player) {
        floorList.forEach(floor -> floor.initializeContainers(lootTable));
        teamList.get(0).getTeamMembersList().add(player.getUniqueId());
        teamList.forEach(Team::teleportTeamMembersToSpawn);
    }

}