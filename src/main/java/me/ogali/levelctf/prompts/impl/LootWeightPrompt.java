package me.ogali.levelctf.prompts.impl;

import lombok.Getter;
import me.ogali.levelctf.containers.domain.Loot;
import me.ogali.levelctf.prompts.domain.ChatPrompt;

public record LootWeightPrompt(@Getter Loot<?> loot) implements ChatPrompt {

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
