package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQsProperties {

    Chat chat;
    KarumiHQs karumiHQs;

    @Before
    public void setUp() {
        chat = mock(Chat.class);
        karumiHQs = new KarumiHQs(chat);
        System.out.println("Initializing Karumi HQ");
    }

    @Property
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwo(int maxibonsGrabbed) {
        Developer developer = new Developer("Xavi", maxibonsGrabbed);
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwoForNotSoHungryDeveloper(
            @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwoForHungryDeveloper(
            @From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwoForManyKarumiDevelopers(
            List<@From(HungryDevelopersGenerator.class) Developer> developers) {
        System.out.println("Number of developers " + developers.size());
        karumiHQs.openFridge(developers);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property(trials = 1000)
    public void theNumberOfMaxibonsInKarumiHQIsAlwaysMoreThanTwoForHungryDeveloperIntense(
            @From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    @Property
    public void theHungryDeveloperSendChatMessage(@From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    @Property
    public void theNotSoHungryDeveloperNeverSendChatMessage(@From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        verify(chat, never()).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

    @Property
    public void theHungryDeveloperNeverSendWrongChatMessage(@From(HungryDevelopersGenerator.class) Developer developer) {
        karumiHQs.openFridge(developer);
        verify(chat, never()).sendMessage("Hi XXX guys, I'm " + developer.getName() + ". We need more maxibons!");
        verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
    }

}
