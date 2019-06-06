package com.gildedrose;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GildedRoseTest {

    @Test
    public void updateQuality_null_throwsNullPointerException() {
        GildedRose app = new GildedRose(null);
        assertThatThrownBy(() -> app.updateQuality()).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void updateQuality_emptyList_name() {
        GildedRose app = new GildedRose(new Item[]{});

        assertThat(app.items).hasSize(0);
    }

    @Test
    public void updateQuality_updatesAllItems() {
        Item a = new Item("a", 10, 10);
        Item b = new Item("b", 20, 10);
        Item c = new Item("c", 30, 10);
        Item[] items = new Item[]{a, b, c};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(9);
        assertThat(app.items[1].quality).isEqualTo(9);
        assertThat(app.items[2].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy1() {
        Item foo = new Item("Aged Brie", 1, 1);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy1ForZero() {
        Item foo = new Item("Aged Brie", 1, 0);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(1);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy2AtSellInDay() {
        Item foo = new Item("Aged Brie", 0, 0);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy2AfterSellIn() {
        Item foo = new Item("Aged Brie", -1, 0);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-2);
        assertThat(app.items[0].quality).isEqualTo(2);
    }


    @Test
    public void updateQuality_AgedBrie_quality_notHigherThan50() {
        Item foo = new Item("Aged Brie", 1, 50);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    public void updateQuality_AgedBrie_qualityNotIncreasingAfter50() {
        Item foo = new Item("Aged Brie", 1, 51);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(51);
    }

//  Default

    @Test
    public void updateQuality_Default_qualityDecreasesBy1() {
        Item foo = new Item("Default Item", 2, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(1);
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_Default_qualityDecreaseBy1() {
        Item foo = new Item("Default Item", 1, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_Default_qualityNotLowerThanZero() {
        Item foo = new Item("Default Item", 1, 0);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void updateQuality_Default_qualityDecreaseBy2AfterSellIn() {
        Item foo = new Item("Default Item", 0, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(8);
    }


//    Backstage passes to a Ramstein concert


    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy1ForMoreThan10Days() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 11, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(10);
        assertThat(app.items[0].quality).isEqualTo(11);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy2For10Days() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 10, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(9);
        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy2For6Days() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 6, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(5);
        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy3For5Days() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 5, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(4);
        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    public void updateQuality_BackstagePass_qualityIncreasesBy3OnLastDay() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 1, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDropsToZero() {
        Item foo = new Item("Backstage passes to a Ramstein concert", 0, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(0);
    }

// Sulfuras, Hand of Ragnaros

    @Test
    public void updateQuality_Sulfuras_qualityIsStableBeforeSellIn() {
        Item foo = new Item("Sulfuras, Hand of Ragnaros", 1, 80);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(1);
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    public void updateQuality_Sulfuras_qualityIsStableForZero() {
        Item foo = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    public void updateQuality_Sulfuras_qualityIsStableAfterSellIn() {
        Item foo = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(80);
    }


//    Conjured


//    @Ignore
    @Test
    public void updateQuality_Conjured_qualityDecreasesBy2() {
        Item foo = new Item("Conjured", 2, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(1);
        assertThat(app.items[0].quality).isEqualTo(8);
    }

//    @Ignore
    @Test
    public void updateQuality_Conjured_qualityDecreasesBy2OnLastDay() {
        Item foo = new Item("Conjured", 1, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(8);
    }

//    @Ignore
    @Test
    public void updateQuality_Conjured_qualityDecreaseBy2AfterSellIn() {
        Item foo = new Item("Conjured", 0, 10);
        Item[] items = new Item[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(6);
    }

}
