package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ogali.levelctf.containers.domain.LootContainer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {

    private Location spawnLocation;
    private final List<LootContainer> lootContainerList;

    public Floor() {
        this.lootContainerList = new ArrayList<>();
    }

}
