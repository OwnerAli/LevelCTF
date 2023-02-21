package me.ogali.levelctf.players.domain;

import lombok.Data;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.items.FloorSpawnPointItem;
import me.ogali.levelctf.items.SpawnPointSetterEditItem;
import me.ogali.levelctf.items.*;
import me.ogali.levelctf.menus.LootSettingsMenu;
import me.ogali.levelctf.prompts.domain.ChatPrompt;
import me.ogali.levelctf.prompts.impl.LootWeightPrompt;
import me.ogali.levelctf.teams.domain.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@Data
public class EditPlayer {

    private final Player player;
    private final Arena editingArena;
    private Team teamSelection;
    private Floor floorSelection;
    private final SpawnPointSetterEditItem spawnPointSetterEditItem = new SpawnPointSetterEditItem(this);

    private Optional<ChatPrompt> chatPromptOptional;

    private ItemStack[] originalInventoryContents;
    private boolean editMode;

    public EditPlayer(Player player, Arena editingArena) {
        this.player = player;
        this.editingArena = editingArena;
        this.originalInventoryContents = player.getInventory().getContents();
        this.teamSelection = new Team(ChatColor.BLUE);
        chatPromptOptional = Optional.empty();
        enableEditMode();
    }

    public void enableEditMode() {
        editMode = true;
        giveItemLoadOut();
    }

    public void disableEditMode() {
        editMode = false;
        player.getInventory().setContents(originalInventoryContents);
        LevelCTF.getInstance().getEditPlayerRegistry().removeEditPlayer(this);
    }

    public void giveItemLoadOut() {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, spawnPointSetterEditItem.getNbtItem().getItem());
        inventory.setItem(1, new FloorSpawnPointItem(this).getNbtItem().getItem());
        inventory.setItem(2, new SetLootTableItem(this).getNbtItem().getItem());
        inventory.setItem(3, new ContainerAddItem(this).getNbtItem().getItem());
        inventory.setItem(8, new SaveEditsItem(this).getNbtItem().getItem());
    }

    public void openLastMenu() {
        chatPromptOptional.ifPresent(chatPrompt -> {
            if (chatPrompt instanceof LootWeightPrompt lootWeightPrompt) {
                new LootSettingsMenu().show(this, editingArena, lootWeightPrompt.loot());
            }
        });
    }

}
