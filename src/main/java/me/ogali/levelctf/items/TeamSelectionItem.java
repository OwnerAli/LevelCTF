package me.ogali.levelctf.items;

import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ColorUtils;
import me.ogali.levelctf.utils.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class TeamSelectionItem extends ItemBuilder {

    public TeamSelectionItem(EditPlayer editPlayer, Team team) {
        super(new ColorUtils().getWoolColorFromChatColor(team.getTeamColor().name()),
                event -> {
                    event.setCancelled(true);

                    if (!event.getPlayer().isSneaking()) return;
                    editPlayer.setTeamSelection(team);

                    PlayerInventory inventory = event.getPlayer().getInventory();

                    inventory.setHeldItemSlot(0);
                    inventory.clear();
                    inventory.setItem(0, editPlayer.getSpawnPointSetterEditItem().build("spawnPointSetterItem"));
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TextComponent(team.getTeamColor() + "SELECTED " + team + " TEAM"));
                    event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                });
        glowing();
        setName(team.getTeamColor() + "" + team + " TEAM");
        addLoreLine("&nRight-Click to select this team.");
        build();
    }

    public void register(String id) {
        LevelCTF.getInstance().getItemRegistry().registerItem(id, this);
    }

}
