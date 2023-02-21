package me.ogali.levelctf.loot.containers;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.loot.domain.Loot;

import java.util.List;
import java.util.Random;

public class LootSelectorByWeight<T> {

    private final List<Loot<T>> items;
    private final Random random;

    public LootSelectorByWeight(List<Loot<T>> items) {
        this.items = items;
        this.random = LevelCTF.getInstance().getRandom();
    }

    public T getRandomItem() {
        int totalWeight = 0;
        for (Loot<T> item : items) {
            totalWeight += item.getWeight();
        }
        int randomWeight = random.nextInt(totalWeight);
        int weightSoFar = 0;
        for (Loot<T> item : items) {
            weightSoFar += item.getWeight();
            if (randomWeight < weightSoFar) {
                return item.getElement();
            }
        }
        return null;
    }
    
}
