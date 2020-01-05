package com.gildedrose;

import com.victory.org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @UseReporter(Junit5Reporter.class)
    @Test
    public void should_update_quality() {
        final var sellIn = 0;
        final var quality = 0;
        CombinationApprovals.verifyAllCombinations(this::doUpdatedQuality,
                new String[]{"foo", "Aged Brie"},
                new Integer[]{sellIn},
                new Integer[]{quality});
    }

    private String doUpdatedQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0].toString();
    }

}
