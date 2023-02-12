package me.ogali.levelctf.containers.domain;

import me.ogali.levelctf.LevelCTF;

import java.util.List;
import java.util.Random;

public class WeightedRandomSelector<T> {
    
    private final List<Loot<T>> items;
    private final double[] cumulativeWeights;
    private final Random random;

    public WeightedRandomSelector(List<Loot<T>> items) {
        this.items = items;
        this.cumulativeWeights = new double[items.size()];
        cumulativeWeights[0] = items.get(0).weight();
        for (int i = 1; i < items.size(); i++) {
            cumulativeWeights[i] = cumulativeWeights[i - 1] + items.get(i).weight();
        }
        this.random = LevelCTF.getInstance().getRandom();
    }

    public T getRandomItem() {
        double randVal = random.nextDouble() * cumulativeWeights[cumulativeWeights.length - 1];
        int idx = binarySearch(cumulativeWeights, randVal);
        return items.get(idx).element();
    }

    private int binarySearch(double[] arr, double val) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
}
