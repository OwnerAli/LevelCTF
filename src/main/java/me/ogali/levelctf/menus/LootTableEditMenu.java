package me.ogali.levelctf.menus;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.GuiItem;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.containers.domain.Loot;
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
                .filter(itemStackLoot -> itemStackLoot.getElement().getType() != Material.AIR)
                .map(Loot::getElement)
                .forEach(itemStack -> guiItemList.add(new GuiItem(itemStack, click -> click.setCancelled(true))));
        paginatedPane.populateWithGuiItems(guiItemList);

        gui.addPane(paginatedPane);
        gui.show(player);
    }


}
