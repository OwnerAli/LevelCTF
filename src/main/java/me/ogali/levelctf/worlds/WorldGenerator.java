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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WorldGenerator extends WorldCreator {

    public WorldGenerator(String schematicName) {
        super(schematicName + "World");
        type(WorldType.FLAT);
        generatorSettings("{\"layers\": [{\"block\": \"air\", \"height\": 100}], \"biome\":\"plains\"}");
        generateStructures(false);
    }

    public org.bukkit.World loadWorldWithSchematic(File schematicFile, BlockVector3 blockVector3) {
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        org.bukkit.World world = this.createWorld();

        world.getBlockAt(0, 0, 0).setType(Material.BEDROCK);

        assert worldEditPlugin != null;

        World adaptedWorld = BukkitAdapter.adapt(world);

        ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);

        try (ClipboardReader reader = format.getReader(new FileInputStream(schematicFile))) {

            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld,
                    -1)) {

                Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                        .to(blockVector3).ignoreAirBlocks(true).build();

                try {
                    Operations.complete(operation);
                    editSession.flushSession();

                } catch (WorldEditException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return world;
    }

}
