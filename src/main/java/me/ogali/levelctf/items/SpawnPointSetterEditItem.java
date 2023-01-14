package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SpawnPointSetterEditItem extends ActionItem {

    public SpawnPointSetterEditItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.BLAZE_ROD)
                        .setName("&e&lSpawn Point Setting Tool")
                        .addLoreLines("&nRight-click &7to select which team", "you would like to set the",
                                "spawn point for.", "&nLeft-Click a block to set", "the teams spawn location.")
                        .build(), "spawnSetterItem",
                event -> {
                    event.setCancelled(true);

                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                        Inventory inventory = event.getPlayer().getInventory();

                        List<ItemStack> teamSelectionItemList = new ArrayList<>();
                        editPlayer.getEditingArena().getTeamList()
                                .forEach(team -> teamSelectionItemList.add(new TeamSelectionItem(editPlayer, team).getNbtItem().getItem()));

                        for (int i = 0; i < teamSelectionItemList.size(); i++) {
                            inventory.setItem(i, teamSelectionItemList.get(i));
                        }
                        return;
                    }
                    if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        editPlayer.getTeamSelection().setSpawnLocation(event.getClickedBlock().getLocation());
                        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                new TextComponent(editPlayer.getTeamSelection().getTeamColor() + "SET "
                                        + editPlayer.getTeamSelection().toString() + " TEAM'S SPAWN POINT"));
                        event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                    }
                });
        register();
    }

}
