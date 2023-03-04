package me.ogali.levelctf.prompts.impl;

import me.ogali.levelctf.loot.domain.Loot;
import me.ogali.levelctf.prompts.domain.ChatPrompt;

public record LootWeightPrompt(Loot<?> loot) implements ChatPrompt {

    @Override
    public void setValue(String value) {
        double doubleValue = Double.parseDouble(value);
        try {
            if (doubleValue < 0) return;
            loot.setWeight(doubleValue);
        } catch (NumberFormatException ignored) {
        }
    }

}
