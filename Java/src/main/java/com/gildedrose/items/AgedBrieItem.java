package com.gildedrose.items;

public class AgedBrieItem extends UpdatableItem {

    private static final int MAXIMUM_QUALITY = 50;

    public AgedBrieItem(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    public void updateQuality() {
        if (sellIn < 0) {
            quality += 2;
        } else {
            quality++;
        }

        if (quality > MAXIMUM_QUALITY) {
            quality = MAXIMUM_QUALITY;
        }
    }
}
