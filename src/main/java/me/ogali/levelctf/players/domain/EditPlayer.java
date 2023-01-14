package me.ogali.levelctf.players.domain;

import lombok.Data;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.items.SpawnPointSetterEditItem;
import me.ogali.levelctf.items.TeamCreationItem;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
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
        giveItemLoadOut();
    }

    private void disableEditMode() {
        editMode = false;
    }

    public void giveItemLoadOut() {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, spawnPointSetterEditItem.getNbtItem().getItem());
        inventory.setItem(1, new TeamCreationItem(this).getNbtItem().getItem());
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

}
