package me.ogali.levelctf;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import lombok.Getter;
import me.ogali.levelctf.actionitems.ItemRegistry;
import me.ogali.levelctf.arenas.ArenaRegistry;
import me.ogali.levelctf.commands.AdminCommands;
import me.ogali.levelctf.commands.PlayerCommands;
import me.ogali.levelctf.games.GameRegistry;
import me.ogali.levelctf.listeners.AdminClickListener;
import me.ogali.levelctf.listeners.PlayerKillPlayerListener;
import me.ogali.levelctf.players.EditPlayerRegistry;
import me.ogali.levelctf.prompts.listeners.ChatPromptListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private GameRegistry gameRegistry;
    @Getter
    private ItemRegistry itemRegistry;
    @Getter
    private Random random;

    @Override
    public void onEnable() {
        instance = this;
        random = new Random();
        initializePlugin();
    }

    @Override
    public void onDisable() {

    }

    private void initializePlugin() {
        initializeRegistries();
        initializeCommands();
        initializeListeners();
        saveDefaultConfig();
        checkWorldEditDependency();
    }

    private void initializeRegistries() {
        arenaRegistry = new ArenaRegistry();
        editPlayerRegistry = new EditPlayerRegistry();
        gameRegistry = new GameRegistry();
        itemRegistry = new ItemRegistry();
    }

    private void initializeCommands() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        commandManager.setFormat(MessageType.SYNTAX, ChatColor.RED, ChatColor.RED);
        commandManager.registerCommand(new AdminCommands(this));
        commandManager.registerCommand(new PlayerCommands(this));
        commandManager.getCommandCompletions().registerCompletion("gameIds", handler -> gameRegistry.getActiveGameIds());
    }

    private void initializeListeners() {
        getServer().getPluginManager().registerEvents(new AdminClickListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKillPlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatPromptListener(), this);
    }

    private void checkWorldEditDependency() {
        if (Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) return;
        getLogger().severe("*** WorldEdit is not installed or not enabled. ***");
        getLogger().severe("*** This plugin will be disabled. ***");
        this.setEnabled(false);
    }

}
