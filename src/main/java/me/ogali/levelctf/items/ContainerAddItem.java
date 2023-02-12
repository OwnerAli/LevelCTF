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
                        .setName("&b&lSet Container")
                        .build(),
                "containerAddItem",
                event -> {
                    if (event.getClickedBlock() == null) return;
                    if (!(event.getClickedBlock().getState() instanceof TileState tileState)) return;
                    if (!(tileState instanceof Container container)) return;
                    event.setCancelled(true);
                    editPlayer.getEditingArena().getFloorList().get(0).getContainerList().add(container);
                });
        register();
    }

}
