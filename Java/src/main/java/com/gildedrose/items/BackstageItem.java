package com.gildedrose.items;

public class BackstageItem extends UpdatableItem {

    private static final int MAXIMUM_QUALITY = 50;

    public BackstageItem(int sellIn, int quality) {
        super("Backstage passes to a Ramstein concert", sellIn, quality);
    }

    public void updateQuality() {
        if (sellIn > 9) {
            quality++;
        } else if (sellIn > 4) {
            quality += 2;
        } else if (sellIn > -1) {
            quality += 3;
        } else {
            quality = 0;
        }
        if (quality > MAXIMUM_QUALITY) {
            quality = MAXIMUM_QUALITY;
        }
    }

}
