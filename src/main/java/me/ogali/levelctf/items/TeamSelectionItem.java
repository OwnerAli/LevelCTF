package me.ogali.levelctf.items;

import me.ogali.levelctf.actionitems.domain.ActionItem;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.teams.domain.Team;
import me.ogali.levelctf.utils.Chat;
import me.ogali.levelctf.utils.ColorUtils;
import me.ogali.levelctf.utils.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.inventory.PlayerInventory;

public class TeamSelectionItem extends ActionItem {

    public TeamSelectionItem(EditPlayer editPlayer, Team team) {
        super(new ItemBuilder(new ColorUtils().getWoolColorFromChatColor(team.getTeamColor().name()))
                        .setName(team.getTeamColor() + "" + team + " TEAM")
                        .addLoreLine("&nRight-Click to select this team.")
                        .build(), team + "TeamSelectionItem",
                event -> {
                    event.setCancelled(true);

                    if (!event.getPlayer().isSneaking()) return;
                    editPlayer.setTeamSelection(team);

                    PlayerInventory inventory = event.getPlayer().getInventory();

                    inventory.setHeldItemSlot(0);
                    inventory.clear();
                    editPlayer.giveItemLoadOut();
                    Chat.sendActionBarWithSound(event.getPlayer(), team.getTeamColor() + "SELECTED " + team + " TEAM");
                });
        register();
    }

}
