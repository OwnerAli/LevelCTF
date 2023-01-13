package me.ogali.levelctf.items;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

//public class TestItem extends ActionItem {
//
//    public TestItem(EditPlayer editPlayer) {
//        super(new ItemStack(Material.BLAZE_ROD), event -> {
//            event.setCancelled(true);
//
//            if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
//                Inventory inventory = editPlayer.getPlayer().getInventory();
//                inventory.setItem(0, new ItemStack(new ColorUtils().getWoolColorFromChatColor(editPlayer.getTeamSelection().getTeamColor().toString())));
//                return;
//            }
//            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
//                editPlayer.getTeamSelection().setSpawnLocation(event.getClickedBlock().getLocation());
//                Chat.tell(editPlayer.getPlayer(), "&aSuccessfully set team " + editPlayer.getTeamSelection().getTeamColor() +
//                        "'s spawn location to: " + event.getClickedBlock().getLocation());
//            }
//        });
//        setId("test");
//        LevelCTF.getInstance().getItemRegistry().registerItem("test", this);
//    }
//
//}
