package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;

public class FlagSpawnPointItem extends ActionItem {

    public FlagSpawnPointItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.GRAY_BANNER)
                        .setName("&7&lSet Flag Spawn Location")
                        .build(),
                editPlayer.hashCode() + ":flagSpawnPointItem",
                click -> {
                    click.setCancelled(true);

                    if (click.getClickedBlock() == null) return;
                    editPlayer.getEditingArena().getFlag().setLocation(click
                            .getClickedBlock().getLocation());
                    Chat.sendActionBarWithSound(editPlayer.getPlayer(), "&aFlag spawn location set!");
                });
        register();
    }

}
