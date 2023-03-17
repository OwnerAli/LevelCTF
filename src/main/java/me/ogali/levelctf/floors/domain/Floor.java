package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.loot.containers.ItemStackLootContainer;
import me.ogali.levelctf.loot.containers.LootContainer;
import me.ogali.levelctf.loot.containers.LootSelectorByWeight;
import me.ogali.levelctf.loot.domain.Loot;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void fillLootContainers(World newMapInstanceWorld) {
        LootSelectorByWeight<ItemStack> lootSelectorByWeight = new LootSelectorByWeight<>(lootTable);
        List<LootContainer<ItemStack>> containersAtLocationsList = getContainerInWorld(newMapInstanceWorld);

        containersAtLocationsList
                .forEach(container -> container.fillContainer(lootSelectorByWeight, 5));
    }

    private List<LootContainer<ItemStack>> getContainerInWorld(World world) {
        return containerLocationsList
                .stream()
                .map(location -> (Container) world.getBlockAt(location.getBlockX(), location.getBlockY(),
                        location.getBlockZ()).getState())
                .map(ItemStackLootContainer::new)
                .collect(Collectors.toList());
    }

}
