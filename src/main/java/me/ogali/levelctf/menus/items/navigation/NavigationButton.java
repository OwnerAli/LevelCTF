package me.ogali.levelctf.menus.items.navigation;

import me.despical.inventoryframework.GuiItem;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public abstract class NavigationButton extends GuiItem {

    public NavigationButton(String displayName, Consumer<InventoryClickEvent> clickEventConsumer) {
        super(new ItemBuilder(Material.ARROW)
                .setName("&a&l" + displayName)
                .build(), clickEventConsumer);
    }

}
