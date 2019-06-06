package com.gildedrose;

import com.gildedrose.items.UpdatableItem;

import static java.util.Arrays.stream;

public class GildedRose {
    UpdatableItem[] items;

    public GildedRose(UpdatableItem... items) {
        this.items = items;
    }

    public void updateQuality() {

        stream(items).forEach(item -> {
            item.sellIn--;
            item.updateQuality();
        });

    }
}
