package com.gildedrose;

import com.gildedrose.items.*;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        UpdatableItem[] UpdatableItems = new UpdatableItem[] {
                new DefaultItem("a", 10, 20), //
                new AgedBrieItem(2, 0), //
                new DefaultItem("a", 5, 7), //
                new LegendaryItem(80), //
                new LegendaryItem( 80),
                new BackstageItem( 15, 20),
                new BackstageItem( 10, 49),
                new BackstageItem( 5, 49),
                // this conjured UpdatableItem does not work properly yet
//                new UpdatableItem("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(UpdatableItems);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (UpdatableItem UpdatableItem : UpdatableItems) {
                System.out.println(UpdatableItem);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
