package me.ogali.levelctf.menus.items.navigation;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class BackButton extends NavigationButton {

    public BackButton(Consumer<InventoryClickEvent> clickEventConsumer) {
        super("Back", clickEventConsumer);
    }

}
