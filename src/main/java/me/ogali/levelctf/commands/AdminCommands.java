package me.ogali.levelctf.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@CommandAlias("lctfadmin")
@CommandPermission("levelctf.admin")
public class AdminCommands extends BaseCommand {

    private final LevelCTF main;

    @Subcommand("arena create")
    @Syntax("<arena id>")
    public void onArenaCreate(Player player, String arenaId) {
        if (main.getArenaRegistry().registerArena(arenaId)) {
            Chat.tell(player, "&aSuccessfully created arena with id: " + arenaId +
                    "\n&7* To edit your arena, execute command /arena edit <arena id>");
            return;
        }
        Chat.tell(player, "&cThere is already an arena with that name!");
    }

    @Subcommand("arena edit")
    @Syntax("<arena id>")
    public void onArenaEdit(Player player, String arenaId) {
        if (!main.getArenaRegistry().isRegisteredArena(arenaId)) {
            Chat.tell(player, "&cThere is no arena with id: " + arenaId);
            return;
        }
        EditPlayer editPlayer = new EditPlayer(player, main.getArenaRegistry().getArenaById(arenaId));
        editPlayer.toggleEditMode();
        main.getEditPlayerRegistry().addEditPlayer(editPlayer);
    }

    @Subcommand("team create")
    @Syntax("<arena id> <team color>")
    public void onTeamCreate(CommandSender sender, String arenaId, String teamColor) {
        main.getArenaRegistry().getArenaById(arenaId).getTeamList()
                .add(new Team(ChatColor.valueOf(teamColor)));
        Chat.tell(sender, ChatColor.valueOf(teamColor) + teamColor.toUpperCase() + " TEAM CREATED!");
    }

    @Subcommand("game start")
    @Syntax("<arena id>")
    public void onGameStart(Player player, String arenaId) {
        Arena arena = main.getArenaRegistry().getArenaById(arenaId);
        arena.getTeamList().get(0).getTeamMembersList().add(player.getUniqueId());
        arena.getTeamList().forEach(Team::teleportTeamMembersToSpawn);
    }

}
