package com.gildedrose.items;

public abstract class UpdatableItem extends Item implements Updatable {

    public UpdatableItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void updateQuality();

}
