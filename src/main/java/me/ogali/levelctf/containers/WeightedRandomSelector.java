package me.ogali.levelctf.containers;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.containers.domain.Loot;

import java.util.*;

public class WeightedRandomSelector<T> {

    private final List<Loot<T>> lootList;
    private final double totalWeight;
    private final Random random = LevelCTF.getInstance().getRandom();

    public WeightedRandomSelector(List<Loot<T>> objectList) {
        this.lootList = new ArrayList<>();
        this.totalWeight = objectList
                .stream()
                .mapToDouble(Loot::getWeight)
                .sum();
    }

    public T getRandom() {
        double randomValue = random.nextDouble() * totalWeight;
        int index = Collections.binarySearch(lootList, new Loot<T>(null, randomValue),
                Comparator.comparingDouble(Loot::getWeight));
        if (index < 0) {
            index = -index - 1;
        }
        return lootList.get(index).getElement();
    }

}
