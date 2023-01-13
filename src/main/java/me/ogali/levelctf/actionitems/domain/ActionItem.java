package me.ogali.levelctf.actionitems.domain;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Data;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Data
public class ActionItem {

    private final ItemStack item;
    private final Consumer<PlayerInteractEvent> playerInteractEventConsumer;
    private final NBTItem nbtItem;

    public ActionItem(ItemStack item, Consumer<PlayerInteractEvent> playerInteractEventConsumer) {
        this.item = item;
        this.playerInteractEventConsumer = playerInteractEventConsumer;
        this.nbtItem = new NBTItem(item);
    }

    public void setId(String id) {
        nbtItem.setString("id", id);
    }

    public ItemStack getItem() {
        return nbtItem.getItem();
    }

}
