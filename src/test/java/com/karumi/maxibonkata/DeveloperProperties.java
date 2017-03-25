package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperties {

    @Property public void theNumberOfMaxibonsIsAlwaysPositiveOrZero(int maxibons) {
        System.out.println(maxibons);
        Developer developer = new Developer("Xavi", maxibons);
        System.out.println(developer.toString());
        assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);
    }
}
