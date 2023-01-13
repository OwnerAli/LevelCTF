package me.ogali.levelctf.arenas.domain;

import lombok.Data;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class Arena {

    private final String arenaId;
    private List<Floor> floorList;
    private List<Team> teamList;

    public Arena(String arenaId) {
        this.arenaId = arenaId;
        this.floorList = new ArrayList<>();
        this.teamList = new ArrayList<>();
    }

}