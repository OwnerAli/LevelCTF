package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.containers.domain.WeightedRandomSelector;
import me.ogali.levelctf.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {

    private Location spawnLocation;
    private final List<Container> containerList;

    public Floor() {
        this.containerList = new ArrayList<>();
    }

    public void initializeContainers(List<Loot<ItemStack>> itemStackLootList) {
        WeightedRandomSelector<ItemStack> weightedRandomSelector = new WeightedRandomSelector<>(itemStackLootList);

        containerList.stream()
                .map(Container::getInventory)
                .forEach(inventory -> {
                    for (int i = 0; i < 5; i++) {
                        int randomSlot = RandomUtils.getRandomEmptySlot(inventory);
                        inventory.setItem(randomSlot, weightedRandomSelector.getRandomItem());
                    }
                });
    }

}
