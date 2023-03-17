package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;

public class SaveEditsItem extends ActionItem {

    public SaveEditsItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.LIME_DYE)
                        .setName("&a&lSave")
                        .addLoreLines("", "&7Click to save edits!")
                        .build(),
                editPlayer.hashCode() + ":saveEditsItem",
                event -> {
                    event.setCancelled(true);
                    editPlayer.disableEditMode();
                });
        register();
    }

}
