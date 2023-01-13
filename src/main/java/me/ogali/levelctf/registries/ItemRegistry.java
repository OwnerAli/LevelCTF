package me.ogali.levelctf.registries;

import me.ogali.levelctf.utils.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    private final Map<String, ItemBuilder> itemsMap = new HashMap<>();

    public void registerItem(String id, ItemBuilder itemBuilder) {
        itemsMap.put(id, itemBuilder);
    }

    public ItemBuilder getSpecialItem(String id) {
        return itemsMap.get(id);
    }

}
