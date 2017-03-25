package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    KarumiHQs karumiHQs;

    @Before
    public void setUp() {
        karumiHQs = new KarumiHQs(new ConsoleChat());
        System.out.println("Initializing Karumi HQ");
    }

    @Property
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwo(int maxibonsGrabbed) {
        Developer developer = new Developer("Xavi", maxibonsGrabbed);
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

}
