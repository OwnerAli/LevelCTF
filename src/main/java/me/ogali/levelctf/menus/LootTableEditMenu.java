package me.ogali.levelctf.menus;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.GuiItem;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.menus.items.LootEditItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LootTableEditMenu {

    public void show(Player player, Arena arena) {
        Gui gui = new Gui(LevelCTF.getInstance(), 6, "&aEditing " + arena.getArenaId() + "'s Loot Table");
        PaginatedPane paginatedPane = new PaginatedPane(0, 0, 9, 6);

        List<GuiItem> guiItemList = new ArrayList<>();
        arena.getLootTable()
                .stream()
                .filter(itemStackLoot -> itemStackLoot.element().getType() != Material.AIR)
                .forEach(loot -> guiItemList.add(new LootEditItem(loot.element().clone(),
                        click -> {
                            click.setCancelled(true);
                            if (click.isLeftClick()) {
                                new LootSettingsMenu().show(player, arena, loot);
                            } else if (click.isRightClick()) {
                                arena.getLootTable().remove(loot);
                            }
                        })));
        paginatedPane.populateWithGuiItems(guiItemList);

        gui.addPane(paginatedPane);
        gui.show(player);
    }


}
