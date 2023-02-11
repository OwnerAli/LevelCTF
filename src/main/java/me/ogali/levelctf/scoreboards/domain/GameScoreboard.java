package me.ogali.levelctf.scoreboards.domain;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class GameScoreboard {

    public void test(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.getEntries().add("TEST");
        player.setScoreboard(scoreboard);
    }

}
