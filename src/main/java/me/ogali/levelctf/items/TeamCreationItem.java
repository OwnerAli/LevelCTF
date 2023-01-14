package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

public class TeamCreationItem extends ActionItem {

    public TeamCreationItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.LEATHER_HELMET)
                        .setName("&f&lTEAM CREATOR")
                        .setLeatherArmorColor(Color.SILVER)
                        .addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE)
                        .build(), "teamCreationItem",
                click -> {
                    click.setCancelled(true);
                    editPlayer.sendClickableTeamSelectionMessage();
                });
        register();
    }

}
