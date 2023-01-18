package me.ogali.levelctf.containers.domain;

import lombok.AllArgsConstructor;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class LootContainer {

    private final WeightedRandomBag<ItemStack> lootTable;

}
