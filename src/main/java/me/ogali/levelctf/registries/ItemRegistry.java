package me.ogali.levelctf.registries;

import de.tr7zw.nbtapi.NBT;
import me.ogali.levelctf.actionitems.domain.ActionItem;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemRegistry {

    private final Set<ActionItem> actionItemsSet = new HashSet<>();

    public void registerItem(ActionItem actionItem) {
        actionItemsSet.add(actionItem);
    }

    public Optional<ActionItem> getActionItemByItemStack(ItemStack itemStack) {
        return actionItemsSet
                .stream()
                .filter(actionItem -> actionItem.getId().equals(NBT.get(itemStack,
                        nbt -> nbt.getString("id"))))
                .findFirst();
    }

}