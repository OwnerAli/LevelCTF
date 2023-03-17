package me.ogali.levelctf.loot.containers;

import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.RandomUtils;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

public record ItemStackLootContainer(Container container) implements LootContainer<ItemStack> {

    @Override
    public void fillContainer(LootSelectorByWeight<ItemStack> lootSelectorByWeight, int lootableItemsAmount) {
        for (int i = 0; i < lootableItemsAmount; i++) {
            int randomSlot = RandomUtils.getRandomEmptySlot(container.getInventory());
            ItemStack randomItem = lootSelectorByWeight.getRandomItem();
            container.getInventory().setItem(randomSlot, randomItem);
            Chat.log("ITEM: " + randomItem);
            Chat.log("FILLED: " + container.getLocation());
        }
    }

}