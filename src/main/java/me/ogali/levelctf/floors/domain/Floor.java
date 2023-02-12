package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.containers.domain.WeightedRandomSelector;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Floor {

    private Location spawnLocation;
    private final List<Container> containerList;

    public Floor(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
        this.containerList = new ArrayList<>();
    }

    public void initializeContainers(List<Loot<ItemStack>> itemStackLootList) {
        WeightedRandomSelector<ItemStack> weightedRandomSelector = new WeightedRandomSelector<>(itemStackLootList);
        Random random = LevelCTF.getInstance().getRandom();

        containerList.forEach(container -> {
            int randomSlot = random.nextInt(container.getInventory().getSize());
            for (int i = 0; i < 5; i++) {
                container.getInventory().setItem(randomSlot, weightedRandomSelector.getRandomItem());
            }
        });
    }

}
