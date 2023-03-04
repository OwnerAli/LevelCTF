package me.ogali.levelctf.floors.domain;

import lombok.Getter;
import lombok.Setter;
import me.ogali.levelctf.loot.containers.LootContainer;
import me.ogali.levelctf.loot.domain.Loot;
import me.ogali.levelctf.loot.containers.LootSelectorByWeight;
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
    private final List<LootContainer<ItemStack>> containerList;

    public Floor() {
        this.containerList = new ArrayList<>();
    }

    public void fillLootContainers(List<Loot<ItemStack>> itemStackLootList) {
        LootSelectorByWeight<ItemStack> lootSelectorByWeight = new LootSelectorByWeight<>(itemStackLootList);

        containerList
                .forEach(container -> container.fillContainer(lootSelectorByWeight, 5));
    }

}
