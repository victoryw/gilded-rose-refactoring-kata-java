package com.gildedrose;

import com.victory.org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @UseReporter(Junit5Reporter.class)
    @Test
    public void should_update_quality() {
        CombinationApprovals.verifyAllCombinations(this::doUpdatedQuality,
                new String[]{"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"},
                new Integer[]{-1, 0, 2, 6, 11},
                new Integer[]{-1, 0, 49, 50});
    }

    private String doUpdatedQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0].toString();
    }

}
