package me.ogali.levelctf.loot.containers;

import me.ogali.levelctf.utils.RandomUtils;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

public record ItemStackLootContainer(Container container) implements LootContainer<ItemStack> {

    @Override
    public void fillContainer(LootSelectorByWeight<ItemStack> lootSelectorByWeight, int lootableItemsAmount) {
        for (int i = 0; i < lootableItemsAmount; i++) {
            int randomSlot = RandomUtils.getRandomEmptySlot(container.getInventory());
            container.getInventory().setItem(randomSlot, lootSelectorByWeight.getRandomItem());
        }
    }

}