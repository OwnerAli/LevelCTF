package me.ogali.levelctf.prompts.listeners;

import me.ogali.levelctf.LevelCTF;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Optional;

public class ChatPromptListener implements Listener {

    @EventHandler
    @Deprecated
    public void onChatPrompt(PlayerChatEvent event) {
        LevelCTF.getInstance().getEditPlayerRegistry().getEditPlayer(event.getPlayer())
                .ifPresent(editPlayer -> editPlayer.getChatPromptOptional().ifPresent(chatPrompt -> {
                    event.setCancelled(true);
                    chatPrompt.setValue(event.getMessage());
                    editPlayer.openLastMenu();
                    editPlayer.setChatPromptOptional(Optional.empty());
                }));
    }

}
