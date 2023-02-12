package me.ogali.levelctf.menus.items;

import me.despical.inventoryframework.GuiItem;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class WeightChangeItem extends GuiItem {

    public WeightChangeItem(double weight, Consumer<InventoryClickEvent> clickEventConsumer) {
        super(new ItemBuilder(Material.PAPER)
                        .setName("&aCurrent Item Weight: " + weight)
                        .addLoreLines("", "&aLeft-Click to change.", "",
                                "&c&lNOTICE", "&fWeight values cannot go below 0", "&fbut CAN go above 100.")
                        .build(), clickEventConsumer);
    }

}
