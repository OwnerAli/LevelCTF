package me.ogali.levelctf.loot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Loot<T> {

    private final T element;
    private double weight;

}