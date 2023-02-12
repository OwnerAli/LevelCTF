package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.Action;

import java.util.List;

public class FloorSpawnPointItem extends ActionItem {

    private int currentFloorSelection = 0;

    public FloorSpawnPointItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.FIREWORK_STAR)
                .setName("&a&lFloor Spawn Point Tool")
                .addLoreLines("&fRight-Click to select team", "&fLeft-Click block to set spawn point.")
                .build(), "floorSpawnPointItem");
        setInteractEventConsumer(event -> {
            event.setCancelled(true);

            List<Floor> floorsList = editPlayer.getEditingArena().getFloorList();
            int amountOfFloors = floorsList.size();

            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (currentFloorSelection + 1 > amountOfFloors) {
                    currentFloorSelection = 0;
                }
                editPlayer.setFloorSelection(floorsList.get(currentFloorSelection));
                currentFloorSelection += 1;
                Chat.sendActionBarWithSound(event.getPlayer(), "&aCurrent Floor Selected: " + currentFloorSelection);
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getClickedBlock() == null) return;
                editPlayer.getFloorSelection().setSpawnLocation(event.getClickedBlock().getLocation());
                Chat.sendActionBarWithSound(event.getPlayer(), "&aFloor " + editPlayer.getEditingArena().getFloorList().indexOf(editPlayer.getFloorSelection())+1 +
                        " spawn point set!");
            }
        });
        register();
    }

}