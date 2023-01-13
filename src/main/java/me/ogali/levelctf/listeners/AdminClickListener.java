package me.ogali.levelctf.listeners;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import lombok.RequiredArgsConstructor;
import me.ogali.levelctf.LevelCTF;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class AdminClickListener implements Listener {

    private final LevelCTF main;

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;
        if (main.getEditPlayerRegistry().getEditPlayer(event.getPlayer()).isEmpty()) return;
        main.getItemRegistry().getSpecialItem(NBT.get(event.getPlayer().getInventory().getItemInMainHand(),
                nbt -> nbt.getString("id"))).getPlayerInteractEventConsumer().accept(event);
    }

}
