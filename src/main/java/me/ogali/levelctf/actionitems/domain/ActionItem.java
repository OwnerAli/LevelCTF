package me.ogali.levelctf.actionitems.domain;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import me.ogali.levelctf.LevelCTF;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class ActionItem {

    private final NBTItem nbtItem;
    protected final String id;
    private final Consumer<PlayerInteractEvent> interactEventConsumer;

    public ActionItem(ItemStack itemStack, String id, Consumer<PlayerInteractEvent> interactEventConsumer) {
        this.nbtItem = new NBTItem(itemStack);
        this.nbtItem.setString("id", id);
        this.id = id;
        this.interactEventConsumer = interactEventConsumer;
    }

    public void accept(PlayerInteractEvent playerInteractEvent) {
        interactEventConsumer.accept(playerInteractEvent);
    }

    public void register() {
        LevelCTF.getInstance().getItemRegistry().registerItem(this);
    }

}
