package me.ogali.levelctf.players.domain;

import lombok.Data;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.floors.domain.Floor;
import me.ogali.levelctf.items.FloorSpawnPointItem;
import me.ogali.levelctf.items.SpawnPointSetterEditItem;
import me.ogali.levelctf.items.TeamCreationItem;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

@Data
public class EditPlayer {

    private final Player player;
    private final Arena editingArena;
    private Team teamSelection;
    private Floor floorSelection;
    private final SpawnPointSetterEditItem spawnPointSetterEditItem = new SpawnPointSetterEditItem(this);

    private Inventory originalInventory;
    private boolean editMode;

    public EditPlayer(Player player, Arena editingArena) {
        this.player = player;
        this.editingArena = editingArena;
        this.originalInventory = player.getInventory();
        this.teamSelection = new Team(ChatColor.BLUE);
        enableEditMode();
    }

    private void enableEditMode() {
        editMode = true;
        giveItemLoadOut();
    }

    private void disableEditMode() {
        editMode = false;
    }

    public void giveItemLoadOut() {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, spawnPointSetterEditItem.getNbtItem().getItem());
        inventory.setItem(1, new TeamCreationItem(this).getNbtItem().getItem());
        inventory.setItem(2, new FloorSpawnPointItem(this).getNbtItem().getItem());
    }

    public void sendClickableTeamSelectionMessage() {
        ComponentBuilder builder = new ComponentBuilder();
        builder.color(net.md_5.bungee.api.ChatColor.GREEN).append("Click to select team color");
        builder.append(Chat.colorize("&c&l[RED]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " RED"));
        builder.append("\n");
        builder.append(Chat.colorize("&a&l[GREEN]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " GREEN"));
        builder.append("\n");
        builder.append(Chat.colorize("&b&l[BLUE]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " BLUE"));
        builder.append("\n");
        builder.append(Chat.colorize("&e&l[YELLOW]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " YELLOW"));
        builder.append("\n");
        builder.append(Chat.colorize("&f&l[WHITE]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " WHITE"));
        builder.append("\n");
        builder.append(Chat.colorize("&7&l[GREY]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " GRAY"));
        builder.append("\n");
        builder.append(Chat.colorize("&8&l[BLACK]")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/lctfadmin team create " + editingArena.getArenaId() + " BLACK"));
        player.spigot().sendMessage(builder.create());
    }

    public void sendClickableFloorSelectionMessage() {
        ComponentBuilder componentBuilder = new ComponentBuilder();
        List<Floor> floorList = editingArena.getFloorList();

        for (int i = 0; i < floorList.size(); i++) {
            componentBuilder.append("&7&l[FLOOR " + i + 1 + "]").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                    "/lctfadmin floor select " + floorList.get(i)));
        }
        player.spigot().sendMessage(componentBuilder.create());
    }

}
