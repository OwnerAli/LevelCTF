package me.ogali.levelctf.flags.domain;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;

@Data
public class Flag {

    private Location location;

    public void spawnFlag() {
        location.getBlock().setType(Material.BLUE_BANNER);
    }

}
