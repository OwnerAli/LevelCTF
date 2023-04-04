package me.ogali.levelctf.runnables;

import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.players.domain.CTFPlayer;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class FlagParticleRunnable extends BukkitRunnable {

    private final LevelCTF main;
    private final CTFPlayer ctfPlayer;

    public void start() {
        runTaskTimer(main, 0, 20);
    }

    @Override
    public void run() {
        if (!ctfPlayer.flag()) this.cancel();
        ctfPlayer.getPlayer().getWorld().spawnParticle(Particle.REDSTONE,
                ctfPlayer.getPlayer().getLocation().clone().add(0, 2, 0), 2,
                new Particle.DustOptions(Color.RED, 2));
    }

}
