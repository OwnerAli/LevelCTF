package me.ogali.levelctf.containers.domain;

import lombok.Getter;

@Getter
public class Loot<T> {

    private final T element;
    private final double weight;

    public Loot(T element, double weight) {
        this.element = element;
        this.weight = weight;
    }

}
