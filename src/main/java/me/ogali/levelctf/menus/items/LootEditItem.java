package me.ogali.levelctf.menus.items;

import me.despical.inventoryframework.GuiItem;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class LootEditItem extends GuiItem {

    public LootEditItem(ItemStack clonedItem, Consumer<InventoryClickEvent> clickEventConsumer) {
        super(new ItemBuilder(clonedItem)
                .addLoreLines("", "&aLeft-Click to edit.",
                        "&cRight-click to delete.")
                .build(), clickEventConsumer);
    }

    public LootEditItem(ItemStack itemStack) {
        super(new ItemBuilder(itemStack)
                .setName("&aEditing: " + (itemStack.getItemMeta() != null ? itemStack.getItemMeta().getDisplayName() :
                        itemStack.getType()))
                        .build(),
                click -> click.setCancelled(true));
    }

}
