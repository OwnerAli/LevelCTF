package me.ogali.levelctf.menus.items.navigation;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class DoneButton extends NavigationButton {

    public DoneButton(Consumer<InventoryClickEvent> clickEventConsumer) {
        super("Done", clickEventConsumer);
    }

}
