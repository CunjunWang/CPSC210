package ca.ubc.cs.cpsc210.servicecard.tests;

import ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Unit tests for FoodServiceCard class
public class FoodServicesCardTest {
    public static final int INITIAL_BALANCE = 10000;
    private FoodServicesCard testCard;

    @Before
    public void setUp() throws Exception {
        testCard = new FoodServicesCard(INITIAL_BALANCE);
    }

    @Test
        public void testXXXXXX() {
            // template for unit tests
    }

    @Test
    public void testConstructor() {
        assertEquals(testCard.getBalance(),INITIAL_BALANCE);
        assertEquals(testCard.getRewardPoints(),0);
    }

    @Test
    public void testReloadOnce() {
        int ReloadValue = 10;
        testCard.reload(ReloadValue);
        assertEquals(testCard.getBalance(), INITIAL_BALANCE + ReloadValue);
    }

    @Test
    public void testReloadManyTimes() {
        int ReloadValue = 10;
        int ReloadTime = 5;

        for(int i = 0; i < ReloadTime; i++) {
            testCard.reload(ReloadValue);
        }

        assertEquals(testCard.getBalance(),INITIAL_BALANCE + ReloadTime * ReloadValue);
    }

    @Test
    public void testCanNotMakePurchase() {
        int Price = 12000;
        assertFalse(testCard.makePurchase(Price));
    }

    @Test
    public void testPurchaseButNoReward() {
        int Price = 1500;
        assertTrue(testCard.makePurchase(Price));
        assertEquals(testCard.getBalance(),8500);
        assertEquals(testCard.getRewardPoints(),1500);
    }

    @Test
    public void testPurchaseAndGetRewardOnce() {
        int Price = 2500;
        assertTrue(testCard.makePurchase(Price));
        assertEquals(testCard.getBalance(),7510);
        assertEquals(testCard.getRewardPoints(),500);
    }


    @Test
    public void testPurchaseAndGetRewardManyTimes(){
        int Price = 5000;
        assertTrue(testCard.makePurchase(Price));
        assertEquals(testCard.getBalance(),5020);
        assertEquals(testCard.getRewardPoints(),1000);
    }

}