package com.gildedrose;

import com.gildedrose.items.*;
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
        GildedRose app = new GildedRose(new UpdatableItem[]{});

        assertThat(app.items).hasSize(0);
    }

    @Test
    public void updateQuality_updatesAllItems() {
        UpdatableItem a = new DefaultItem("a", 10, 10);
        UpdatableItem b = new DefaultItem("b", 20, 10);
        UpdatableItem c = new DefaultItem("c", 30, 10);

        GildedRose app = new GildedRose(a, b, c);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(9);
        assertThat(app.items[1].quality).isEqualTo(9);
        assertThat(app.items[2].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy1() {
        UpdatableItem foo = new AgedBrieItem(1, 1);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy1ForZero() {
        UpdatableItem foo = new AgedBrieItem(1, 0);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(1);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy2AtSellInDay() {
        UpdatableItem foo = new AgedBrieItem(0, 0);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    public void updateQuality_AgedBrie_qualityIncreasesBy2AfterSellIn() {
        UpdatableItem foo = new AgedBrieItem(-1, 0);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-2);
        assertThat(app.items[0].quality).isEqualTo(2);
    }


    @Test
    public void updateQuality_AgedBrie_quality_notHigherThan50() {
        UpdatableItem foo = new AgedBrieItem(1, 50);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(50);
    }


//  Default

    @Test
    public void updateQuality_Default_qualityDecreasesBy1() {
        UpdatableItem foo = new DefaultItem("a", 2, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(1);
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_Default_qualityDecreaseBy1() {
        UpdatableItem foo = new DefaultItem("a", 1, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    public void updateQuality_Default_qualityNotLowerThanZero() {
        UpdatableItem foo = new DefaultItem("a", 1, 0);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    public void updateQuality_Default_qualityDecreaseBy2AfterSellIn() {
        UpdatableItem foo = new DefaultItem("a", 0, 10);
        UpdatableItem[] items = new UpdatableItem[]{foo};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(8);
    }


//    Backstage passes to a Ramstein concert


    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy1ForMoreThan10Days() {
        UpdatableItem foo = new BackstageItem(11, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(10);
        assertThat(app.items[0].quality).isEqualTo(11);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy2For10Days() {
        UpdatableItem foo = new BackstageItem(10, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(9);
        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy2For6Days() {
        UpdatableItem foo = new BackstageItem(6, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(5);
        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDecreaseBy3For5Days() {
        UpdatableItem foo = new BackstageItem(5, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(4);
        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    public void updateQuality_BackstagePass_qualityIncreasesBy3OnLastDay() {
        UpdatableItem foo = new BackstageItem(1, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    public void updateQuality_BackstagePass_qualityDropsToZero() {
        UpdatableItem foo = new BackstageItem(0, 10);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(0);
    }

// Sulfuras, Hand of Ragnaros

    @Test
    public void updateQuality_Sulfuras_qualityIsStableBeforeSellIn() {
        UpdatableItem foo = new LegendaryItem(80);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    public void updateQuality_Sulfuras_qualityIsStableForZero() {
        UpdatableItem foo = new LegendaryItem(80);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(-1);
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    public void updateQuality_Sulfuras_qualityIsStableAfterSellIn() {
        UpdatableItem foo = new LegendaryItem(80);

        GildedRose app = new GildedRose(foo);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(80);
    }


//    Conjured

//
//    @Ignore
//    @Test
//    public void updateQuality_Conjured_qualityDecreasesBy2() {
//        UpdatableItem foo = new UpdatableItem("Conjured", 2, 10);
//        UpdatableItem[] items = new UpdatableItem[]{foo};
//
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//
//        assertThat(app.items[0].sellIn).isEqualTo(1);
//        assertThat(app.items[0].quality).isEqualTo(8);
//    }
//
//    @Ignore
//    @Test
//    public void updateQuality_Conjured_qualityDecreasesBy2OnLastDay() {
//        UpdatableItem foo = new UpdatableItem("Conjured", 1, 10);
//        UpdatableItem[] items = new UpdatableItem[]{foo};
//
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//
//        assertThat(app.items[0].sellIn).isEqualTo(0);
//        assertThat(app.items[0].quality).isEqualTo(8);
//    }
//
//    @Ignore
//    @Test
//    public void updateQuality_Conjured_qualityDecreaseBy2AfterSellIn() {
//        UpdatableItem foo = new UpdatableItem("Conjured", 0, 10);
//        UpdatableItem[] items = new UpdatableItem[]{foo};
//
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//
//        assertThat(app.items[0].sellIn).isEqualTo(-1);
//        assertThat(app.items[0].quality).isEqualTo(6);
//    }

}
