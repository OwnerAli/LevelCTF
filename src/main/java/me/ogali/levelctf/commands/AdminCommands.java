package me.ogali.levelctf.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@CommandAlias("lctfadmin")
@CommandPermission("levelctf.admin")
public class AdminCommands extends BaseCommand {

    private final LevelCTF main;

    @Subcommand("arena create")
    @Syntax("Usage: /arena create <arena id>")
    public void onArenaCreate(Player player, String arenaId) {
        if (main.getArenaRegistry().registerArena(arenaId)) {
            Chat.tell(player, "&aSuccessfully created arena with id: " + arenaId +
                    "\n&7* To edit your arena, execute command /arena edit <arena id>");
            return;
        }
        Chat.tell(player, "&cThere is already an arena with that name!");
    }

    @Subcommand("arena edit")
    @Syntax("Usage: /arena edit <arena id>")
    public void onArenaEdit(Player player, String arenaId) {
        if (!main.getArenaRegistry().isRegisteredArena(arenaId)) {
            Chat.tell(player, "&cThere is no arena with id: " + arenaId);
            return;
        }
        EditPlayer editPlayer = new EditPlayer(player, main.getArenaRegistry().getArenaById(arenaId));
        editPlayer.toggleEditMode();
        main.getEditPlayerRegistry().addEditPlayer(editPlayer);
    }


}
