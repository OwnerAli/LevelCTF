package me.ogali.levelctf.loot.containers;

public interface LootContainer<T> {

    void fillContainer(LootSelectorByWeight<T> lootSelectorByWeight, int lootableItemsAmount);

}
