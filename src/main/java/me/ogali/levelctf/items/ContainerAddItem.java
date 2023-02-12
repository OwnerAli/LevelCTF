package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.block.TileState;

public class ContainerAddItem extends ActionItem {

    public ContainerAddItem(EditPlayer editPlayer) {
        super(new ItemBuilder(Material.TRIPWIRE_HOOK)
                        .setName("&b&lAdd Container To Selected Floor")
                        .build(),
                "containerAddItem",
                event -> {
                    if (event.getClickedBlock() == null) return;
                    if (!(event.getClickedBlock().getState() instanceof TileState tileState)) return;
                    if (!(tileState instanceof Container container)) return;
                    event.setCancelled(true);
                    editPlayer.getFloorSelection().getContainerList().add(container);
                });
        register();
    }

}
