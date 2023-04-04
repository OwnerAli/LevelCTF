package me.ogali.levelctf.listeners;

import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class AdminClickListener implements Listener {

    private final LevelCTF main;

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType()
                .equals(Material.BLUE_BANNER)) {
            if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            main.getGameRegistry().getGameByPlayer(event.getPlayer())
                    .flatMap(game -> game.getCtfPlayerByPlayer(event.getPlayer()))
                    .ifPresent(ctfPlayer -> ctfPlayer.interactWithFlag(event.getClickedBlock()));
        }
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;
        if (main.getEditPlayerRegistry().getEditPlayer(event.getPlayer()).isEmpty()) return;
        main.getItemRegistry().getActionItemByItemStack(event.getItem())
                .ifPresent(actionItem -> actionItem.accept(event));
    }

}