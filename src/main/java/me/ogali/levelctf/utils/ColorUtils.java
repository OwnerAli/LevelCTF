package me.ogali.levelctf.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ColorUtils {

    public Material getWoolColorFromChatColor(String teamColor) {
        return Material.getMaterial(teamColor.toUpperCase() + "_WOOL");
    }

}
