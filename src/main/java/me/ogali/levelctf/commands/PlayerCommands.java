package me.ogali.levelctf.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@CommandAlias("levelctf|lctf")
@CommandPermission("levelctf.player")
@SuppressWarnings("unused")
public class PlayerCommands extends BaseCommand {

    private final LevelCTF main;

    @Subcommand("game join")
    @CommandCompletion("@gameIds")
    public void onGameJoin(Player player, String gameId) {
        main.getGameRegistry().getGameById(gameId)
                .ifPresent(game -> game.addPlayer(player));
    }

}
