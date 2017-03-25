package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    KarumiHQs karumiHQs;

    @Before
    public void setUp() {
        karumiHQs = new KarumiHQs(new ConsoleChat());
        System.out.println("Initializing Karumi HQ");
    }

    @Property public void theNumberOfMaxibonsIsAlwaysPositiveOrZero(int maxibons) {
        System.out.println(maxibons);
        Developer developer = new Developer("Xavi", maxibons);
        System.out.println(developer.toString());
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }

    @Property public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwo(int maxibonsGrabbed) {
        Developer developer = new Developer("Xavi", maxibonsGrabbed);
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Test
    public void testConsumedMaxibonsIsCorrect() {
        int maxibons = 3;
        Developer developer = new Developer("Xavi", maxibons);
        KarumiHQs karumiHQs = new KarumiHQs(new ConsoleChat());
        int maxibonsLeftBefore = karumiHQs.getMaxibonsLeft();
        karumiHQs.openFridge(developer);
        int maxibonsLeftAfter = karumiHQs.getMaxibonsLeft();
        assertEquals(maxibonsLeftAfter, maxibonsLeftBefore - maxibons);
    }
}
