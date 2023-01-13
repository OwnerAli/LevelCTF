package me.ogali.levelctf.players.domain;

import lombok.Data;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.items.SpawnPointSetterEditItem;
import me.ogali.levelctf.items.TeamSelectionItem;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Data
public class EditPlayer {

    private final Player player;
    private final Arena editingArena;
    private Team teamSelection;
    private final SpawnPointSetterEditItem spawnPointSetterEditItem = new SpawnPointSetterEditItem(this);

    private Inventory originalInventory;
    private boolean editMode;

    public EditPlayer(Player player, Arena editingArena) {
        this.player = player;
        this.editingArena = editingArena;
        this.originalInventory = player.getInventory();
        this.teamSelection = new Team(ChatColor.BLUE);
    }

    public void toggleEditMode() {
        if (editMode) {
            disableEditMode();
            return;
        }
        enableEditMode();
    }

    private void enableEditMode() {
        editMode = true;
        Inventory inventory = player.getInventory();
        inventory.setItem(0, spawnPointSetterEditItem.build("spawnPointSetterItem"));
    }

    private void disableEditMode() {
        editMode = false;
    }

}
