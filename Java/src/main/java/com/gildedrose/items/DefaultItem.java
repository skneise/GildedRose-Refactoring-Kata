package com.gildedrose.items;

public class DefaultItem extends UpdatableItem {

    public DefaultItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void updateQuality() {
        if (sellIn < 0) {
            if (quality > 0) {
                quality -= 2;
            }
        } else {
            quality--;
        }

        if (quality < 0) {
            quality = 0;
        }
    }
}
