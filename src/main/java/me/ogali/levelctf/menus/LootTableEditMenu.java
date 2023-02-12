package me.ogali.levelctf.menus;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.GuiItem;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.inventoryframework.pane.StaticPane;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.menus.items.LootEditItem;
import me.ogali.levelctf.menus.items.navigation.DoneButton;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LootTableEditMenu {

    public void show(EditPlayer editPlayer, Arena arena) {
        Gui gui = new Gui(LevelCTF.getInstance(), 6, Chat.colorize("&aEditing " + arena.getArenaId() + "'s Loot Table"));
        StaticPane staticPane = new StaticPane(0, 0, 9, 6);
        PaginatedPane paginatedPane = new PaginatedPane(0, 0, 9, 5);
        gui.setOnGlobalClick(click -> click.setCancelled(true));

        List<GuiItem> guiItemList = new ArrayList<>();
        arena.getLootTable()
                .stream()
                .filter(itemStackLoot -> itemStackLoot.getElement().getType() != Material.AIR)
                .forEach(loot -> guiItemList.add(new LootEditItem(loot.getElement().clone(),
                        click -> {
                            click.setCancelled(true);
                            if (click.isLeftClick()) {
                                new LootSettingsMenu().show(editPlayer, arena, loot);
                            } else if (click.isRightClick()) {
                                arena.getLootTable().remove(loot);
                            }
                        })));
        paginatedPane.populateWithGuiItems(guiItemList);

        staticPane.addItem(new DoneButton(click -> editPlayer.getPlayer().closeInventory()), 4, 5);

        gui.addPane(staticPane);
        gui.addPane(paginatedPane);
        gui.show(editPlayer.getPlayer());
    }


}
