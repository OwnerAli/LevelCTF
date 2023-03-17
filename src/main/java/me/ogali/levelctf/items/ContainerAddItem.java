package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.block.TileState;

public class ContainerAddItem extends ActionItem {

    public ContainerAddItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.TRIPWIRE_HOOK)
                        .setName("&b&lAdd Container To Selected Floor")
                        .build(),
                editPlayer.hashCode() + ":containerAddItem",
                event -> {
                    if (event.getClickedBlock() == null) return;
                    if (!(event.getClickedBlock().getState() instanceof TileState tileState)) {
                        Chat.tell(editPlayer.getPlayer(), "&cThat isn't a container!");
                        return;
                    }
                    if (!(tileState instanceof Container)) {
                        Chat.tell(editPlayer.getPlayer(), "&cThat isn't a container!");
                        return;
                    }
                    event.setCancelled(true);
                    editPlayer.getFloorSelection().getContainerLocationsList().add(event.getClickedBlock().getLocation());
                });
        register();
    }

}