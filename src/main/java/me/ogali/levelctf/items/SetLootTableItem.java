package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.menus.LootTableEditMenu;
import me.ogali.levelctf.players.domain.EditPlayer;
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
                        .setName("Set Loot Table")
                        .addLoreLines("", "&aLeft-Click any container with items",
                                "&ain it. The items in the", "&acontainer will become the new",
                                "&aloot table for this arena.")
                        .build(),
                "lootTableItem",
                click -> {
                    click.setCancelled(true);
                    if (click.getClickedBlock() == null) return;
                    if (!(click.getClickedBlock().getState() instanceof TileState tileState)) return;
                    if (!(tileState instanceof Container container)) return;
                    if (container.getInventory().isEmpty()) return;
                    List<Loot<ItemStack>> containerLootList =
                            Arrays.stream(container.getInventory().getContents())
                                    .filter(Objects::nonNull)
                                    .filter(itemStack -> itemStack.getType() != Material.AIR)
                                    .map(itemStack -> new Loot<>(itemStack, 100.0))
                                    .toList();
                    editPlayer.getEditingArena().getLootTable().addAll(containerLootList);
                    new LootTableEditMenu().show(editPlayer, editPlayer.getEditingArena());
                });
        register();
    }

}
