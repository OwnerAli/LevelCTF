package me.ogali.levelctf.utils;

import me.ogali.levelctf.LevelCTF;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class RandomUtils {

    public static int getRandomEmptySlot(Inventory inventory) {
        Random random = LevelCTF.getInstance().getRandom();
        int maxIterations = inventory.getSize();
        for (int i = 0; i < maxIterations; i++) {
            int randomSlot = random.nextInt(inventory.getSize());
            if (inventory.getItem(randomSlot) == null) {
                return randomSlot;
            }
        }
        return -1;
    }

}
