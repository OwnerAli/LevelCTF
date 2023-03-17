package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.loot.containers.ItemStackLootContainer;
import me.ogali.levelctf.loot.containers.LootContainer;
import me.ogali.levelctf.loot.containers.LootSelectorByWeight;
import me.ogali.levelctf.loot.domain.Loot;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {

    private Location spawnLocation;
    private final List<Loot<ItemStack>> lootTable;
    private final List<Location> containerLocationsList;

    public Floor() {
        this.lootTable = new ArrayList<>();
        this.containerLocationsList = new ArrayList<>();
    }

    public void fillLootContainers(World world) {
        LootSelectorByWeight<ItemStack> lootSelectorByWeight = new LootSelectorByWeight<>(lootTable);
        List<LootContainer<ItemStack>> containersAtLocationsList = getContainerInWorld(world);

        containersAtLocationsList
                .forEach(container -> container.fillContainer(lootSelectorByWeight, 5));
    }

    private List<LootContainer<ItemStack>> getContainerInWorld(World world) {
        List<LootContainer<ItemStack>> containerList = new ArrayList<>();
        for (Location location : containerLocationsList) {
            Container containerAtLocation = (Container) world.getBlockAt(location.getBlockX(), location.getBlockY(),
                    location.getBlockZ()).getState();
            containerList.add(new ItemStackLootContainer(containerAtLocation));
        }
        return containerList;
    }

}
