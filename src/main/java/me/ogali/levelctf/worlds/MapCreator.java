package me.ogali.levelctf.worlds;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import lombok.Getter;
import me.ogali.levelctf.LevelCTF;
import org.bukkit.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MapCreator {

    private final String schematicName;
    private int numberOfInstances;

    @Getter
    private org.bukkit.World editWorld;

    public MapCreator(String schematicName) {
        this.schematicName = schematicName;
    }

    public org.bukkit.World createNewMapInstance(boolean editMap) {
        org.bukkit.World world;

        if (editMap) {
            world = new FlatWorldGenerator(schematicName + "EditMap").createWorld();
            world.getBlockAt(0, 0, 0).setType(Material.BEDROCK);
            this.editWorld = world;
        } else {
            world = new FlatWorldGenerator(schematicName + "World" + numberOfInstances++)
                    .createWorld();
        }

        if (world == null) throw new RuntimeException();
        pasteSchematic(world);
        return world;
    }

    public void pasteSchematic(org.bukkit.World world) {
        File schematicFile = new File(LevelCTF.getInstance().getDataFolder()
                .getAbsolutePath() + "/map-schematics/" + schematicName + ".schem");

        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");

        assert worldEditPlugin != null;

        World adaptedWorld = BukkitAdapter.adapt(world);

        ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);

        try (ClipboardReader reader = format.getReader(new FileInputStream(schematicFile))) {

            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld,
                    -1)) {

                Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                        .to(BlockVector3.at(0, 0, 0)).ignoreAirBlocks(true).build();

                try {
                    Operations.complete(operation);
                    editSession.flushSession();

                } catch (WorldEditException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
