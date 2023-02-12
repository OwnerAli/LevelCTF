package me.ogali.levelctf.menus;

import me.despical.inventoryframework.Gui;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.utils.Chat;
import org.bukkit.entity.Player;

public class LootSettingsMenu {

    public void show(Player player, Arena arena, Loot<?> loot) {
        Gui gui = new Gui(LevelCTF.getInstance(), 3, Chat.colorize("&aLoot Settings Menu"));



    }

}
