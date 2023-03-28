package me.ogali.levelctf.runnables;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.games.domain.Game;
import me.ogali.levelctf.players.domain.CTFPlayer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartRunnable extends BukkitRunnable {

    private final Game game;
    private long secondsUntilGameStart;

    public GameStartRunnable(Game game, int secondsUntilGameStart) {
        this.game = game;
        this.secondsUntilGameStart = secondsUntilGameStart;
    }

    public void startCountdown() {
        runTaskTimer(LevelCTF.getInstance(), 20, 20);
    }

    @Override
    public void run() {
        if (secondsUntilGameStart == 0) {
            game.start(Bukkit.getPlayer("xxAli"));
            this.cancel();
            return;
        }
        for (CTFPlayer ctfPlayer : game.getPlayerSet()) {
            ctfPlayer.sendTitleMessage("&6&lGame Starting in: &e" + secondsUntilGameStart);
        }
        secondsUntilGameStart--;
    }

}
