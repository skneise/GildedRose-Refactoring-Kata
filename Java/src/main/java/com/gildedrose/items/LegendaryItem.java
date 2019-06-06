package com.gildedrose.items;

public class LegendaryItem extends UpdatableItem {

    public LegendaryItem(int quality) {
        super("Sulfuras, Hand of Ragnaros", 0, quality);
    }

    public void updateQuality() {
        // quality never changes
    }
}
