package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.Action;

import java.util.List;

public class SpawnPointSetterEditItem extends ActionItem {

    private int currentTeamSelection = 0;

    public SpawnPointSetterEditItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.FIRE_CHARGE)
                .setName("&e&lSpawn Point Setting Tool")
                .addLoreLines("&nRight-click &7to select which team", "you would like to set the",
                        "spawn point for.", "&nLeft-Click a block to set", "the teams spawn location.")
                .build(), "spawnSetterItem");
        setInteractEventConsumer(event -> {
            event.setCancelled(true);

            List<Team> teamList = editPlayer.getEditingArena().getTeamList();
            int amountOfFloors = teamList.size();

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (currentTeamSelection + 1 > amountOfFloors) {
                    currentTeamSelection = 0;
                }
                editPlayer.setTeamSelection(teamList.get(currentTeamSelection));
                currentTeamSelection += 1;
                Chat.sendActionBarWithSound(event.getPlayer(), "&aCurrent Team Selected: " + editPlayer.getTeamSelection().toStringColored());
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getClickedBlock() == null) return;
                editPlayer.getTeamSelection().setSpawnLocation(event.getClickedBlock().getLocation());
                Chat.sendActionBarWithSound(event.getPlayer(), editPlayer.getTeamSelection().toStringColored() + " team spawn point set");
            }
        });
        register();
    }

}
