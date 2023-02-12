package me.ogali.levelctf.containers.domain;

import java.util.Random;

public class WeightedRandomBag<T> {
    
    private final T[] items;
    private final double[] cumulativeWeights;
    private final Random rand;

    public WeightedRandomBag(T[] items, double[] weights) {
        this.items = items;
        this.cumulativeWeights = new double[weights.length];
        cumulativeWeights[0] = weights[0];
        for (int i = 1; i < weights.length; i++) {
            cumulativeWeights[i] = cumulativeWeights[i - 1] + weights[i];
        }
        this.rand = new Random();
    }

    public T getRandomItem() {
        double randVal = rand.nextDouble() * cumulativeWeights[cumulativeWeights.length - 1];
        int idx = binarySearch(cumulativeWeights, randVal);
        return items[idx];
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
