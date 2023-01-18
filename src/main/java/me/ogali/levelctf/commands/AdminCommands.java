package me.ogali.levelctf.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.floors.domain.Floor;
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
    @Syntax("<arena id> <min amount of floors> <max amount of floors> <number of teams> number of floors>")
    public void onArenaCreate(Player player, String arenaId, int minAmountOfPlayers, int maxAmountOfPlayers, int numberOfTeams, int numberOfFloors) {
        if (main.getArenaRegistry().registerArena(new Arena(arenaId, minAmountOfPlayers, maxAmountOfPlayers, numberOfTeams, numberOfFloors))) {
            Chat.tell(player, "&aSuccessfully created arena with id: " + arenaId +
                    "\n&7* To edit your arena, execute command /arena edit <arena id>");
            return;
        }
        Chat.tell(player, "&cThere is already an arena with that name!");
    }

    @Subcommand("arena edit")
    @Syntax("<arena id>")
    public void onArenaEdit(Player player, String arenaId) {
        main.getArenaRegistry().getArenaById(arenaId)
                .ifPresentOrElse(arena -> main.getEditPlayerRegistry().addEditPlayer(new EditPlayer(player, arena)),
                        () -> Chat.tell(player, "&cThere is no arena with id: " + arenaId));
    }

    @Subcommand("team create")
    @Syntax("<arena id> <team color>")
    public void onTeamCreate(CommandSender sender, String arenaId, String teamColor) {
        main.getArenaRegistry().getArenaById(arenaId)
                .ifPresentOrElse(arena -> {
                    arena.getTeamList().add(new Team(ChatColor.valueOf(teamColor)));
                    Chat.tell(sender, ChatColor.valueOf(teamColor) + teamColor.toUpperCase() + " TEAM CREATED!");
                }, () -> Chat.tell(sender, "&cThere is no arena with id: " + arenaId));
    }

    @Subcommand("floor create")
    @Syntax("<arena id>")
    public void onFloorSelect(CommandSender sender, String arenaId) {
        main.getArenaRegistry().getArenaById(arenaId)
                .ifPresentOrElse(arena -> {
                    arena.getFloorList().add(new Floor());
                    Chat.tell(sender, "&aSuccessfully created new floor!");
                }, () -> Chat.tell(sender, "&cThere is no arena with id: " + arenaId));
    }

    @Subcommand("game start")
    @Syntax("<arena id>")
    public void onGameStart(Player player, String arenaId) {
    }

}
