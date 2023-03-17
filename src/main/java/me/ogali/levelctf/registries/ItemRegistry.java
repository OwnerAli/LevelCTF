package me.ogali.levelctf.registries;

import de.tr7zw.nbtapi.NBT;
import me.ogali.levelctf.actionitems.domain.ActionItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public void clearEditPlayerItems(int editPlayerHashCode) {
        actionItemsSet
                .forEach(actionItem -> {
                    String[] splitItemId = actionItem.getId().split(":");

                    if (Integer.parseInt(splitItemId[1]) != (editPlayerHashCode)) return;
                    actionItemsSet.remove(actionItem);
                });
    }

}