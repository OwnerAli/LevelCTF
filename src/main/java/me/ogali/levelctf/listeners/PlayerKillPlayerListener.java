package me.ogali.levelctf.listeners;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.players.domain.CTFPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerKillPlayerListener implements Listener {

    private final LevelCTF main;

    public PlayerKillPlayerListener(LevelCTF main) {
        this.main = main;
    }

    @EventHandler
    public void onKill(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player killer)) return;
        if (!(event.getEntity() instanceof Player victim)) return;
        if (!(victim.getHealth() - event.getDamage() < 1)) return;
        main.getGameRegistry().getGameByPlayer(killer)
                .ifPresent(game -> {
                    event.setCancelled(true);
                    game.getCtfPlayerByPlayer(killer)
                            .ifPresent(CTFPlayer::healAndTeleportToNextFloor);
                    game.getCtfPlayerByPlayer(victim)
                            .ifPresent(CTFPlayer::teleportPlayerToPrevFloor);
                });
    }

}
