package me.ogali.levelctf;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import lombok.Getter;
import me.ogali.levelctf.commands.AdminCommands;
import me.ogali.levelctf.listeners.AdminClickListener;
import me.ogali.levelctf.prompts.listeners.ChatPromptListener;
import me.ogali.levelctf.registries.EditPlayerRegistry;
import me.ogali.levelctf.registries.ArenaRegistry;
import me.ogali.levelctf.registries.ItemRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class LevelCTF extends JavaPlugin {

    @Getter
    private static LevelCTF instance;
    @Getter
    private ArenaRegistry arenaRegistry;
    @Getter
    private EditPlayerRegistry editPlayerRegistry;
    @Getter
    private ItemRegistry itemRegistry;
    @Getter
    private Random random;

    @Override
    public void onEnable() {
        instance = this;
        random = new Random();
        initializePlugin();
        if (!isWorldEditEnabled()) {
            this.onDisable();
        }
    }

    @Override
    public void onDisable() {

    }

    private void initializePlugin() {
        initializeRegistries();
        initializeCommands();
        initializeListeners();
        saveDefaultConfig();
    }

    private void initializeRegistries() {
        arenaRegistry = new ArenaRegistry();
        editPlayerRegistry = new EditPlayerRegistry();
        itemRegistry = new ItemRegistry();
    }

    private void initializeCommands() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        commandManager.setFormat(MessageType.SYNTAX, ChatColor.RED, ChatColor.RED);
        commandManager.registerCommand(new AdminCommands(this));
    }

    private void initializeListeners() {
        getServer().getPluginManager().registerEvents(new AdminClickListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatPromptListener(), this);
    }

    private boolean isWorldEditEnabled() {
        Plugin worldEditPlugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
        return worldEditPlugin != null;
    }

}
