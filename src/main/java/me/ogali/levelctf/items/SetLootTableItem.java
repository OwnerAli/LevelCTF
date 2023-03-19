package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.loot.domain.Loot;
import me.ogali.levelctf.menus.LootTableEditMenu;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.block.TileState;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SetLootTableItem extends ActionItem {

    public SetLootTableItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.CHEST)
                        .setName("&6&lSet Floor Loot Table")
                        .addLoreLines("", "&aLeft-Click any container with items",
                                "&ain it. The items in the", "&acontainer will become the new",
                                "&aloot table for this arena.")
                        .build(),
                editPlayer.hashCode() + ":lootTableItem",
                click -> {
                    click.setCancelled(true);
                    if (editPlayer.getFloorSelection() == null) {
                        editPlayer.message("&cYou have to select a floor first! (Right-Click '&a&lFloor Spawn Point Tool&c')");
                        return;
                    }
                    if (click.getClickedBlock() == null) return;
                    if (!(click.getClickedBlock().getState() instanceof TileState tileState)) return;
                    if (!(tileState instanceof Container container)) return;
                    if (container.getInventory().isEmpty()) return;
                    List<Loot<ItemStack>> lootList = Arrays.stream(container.getInventory().getContents())
                            .filter(Objects::nonNull)
                            .filter(itemStack -> itemStack.getType() != Material.AIR)
                            .map(itemStack -> {
                                Chat.log("MAPPED: " + itemStack);
                                return new Loot<>(itemStack, 100.0);
                            })
                            .toList();
                    editPlayer.getFloorSelection().setLootTable(lootList);
                    new LootTableEditMenu().show(editPlayer);
                });
        register();
    }

}
