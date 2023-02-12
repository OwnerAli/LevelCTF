package me.ogali.levelctf.menus;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.pane.StaticPane;
import me.ogali.levelctf.LevelCTF;
import me.ogali.levelctf.arenas.domain.Arena;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.menus.items.WeightChangeItem;
import me.ogali.levelctf.menus.items.navigation.BackButton;
import me.ogali.levelctf.players.domain.EditPlayer;
import me.ogali.levelctf.prompts.impl.LootWeightPrompt;
import me.ogali.levelctf.utils.Chat;

import java.util.Optional;

public class LootSettingsMenu {

    public void show(EditPlayer editPlayer, Arena arena, Loot<?> loot) {
        Gui gui = new Gui(LevelCTF.getInstance(), 4, Chat.colorize("&aLoot Settings Menu"));
        StaticPane staticPane = new StaticPane(0, 0, 9, 4);
        gui.setOnGlobalClick(click -> click.setCancelled(true));

        staticPane.addItem(new WeightChangeItem(loot.getWeight(),
                click -> {
                    editPlayer.getPlayer().closeInventory();
                    Chat.tell(editPlayer.getPlayer(), "&aPlease enter the new weight below: ");
                    editPlayer.setChatPromptOptional(Optional.of(new LootWeightPrompt(loot)));
                }), 4, 1);

        staticPane.addItem(new BackButton(click -> new LootTableEditMenu().show(editPlayer, arena)), 4, 3);

        gui.addPane(staticPane);
        gui.show(editPlayer.getPlayer());
    }

}
