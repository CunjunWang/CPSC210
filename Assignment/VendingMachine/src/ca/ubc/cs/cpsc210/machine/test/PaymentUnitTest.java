package ca.ubc.cs.cpsc210.machine.test;

import ca.ubc.cs.cpsc210.machine.model.Coin;
import ca.ubc.cs.cpsc210.machine.model.PaymentUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentUnitTest {
    private PaymentUnit paymentUnit;

    @Before
    public void setUp() {
        paymentUnit = new PaymentUnit();
    }

    @Test
    public void testXXXXXX() {
        // template
    }

    @Test
    public void testConstructor() {
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL),0);
        assertEquals(paymentUnit.getCurrentTransaction().size(),0);
    }

    @Test
    public void testclearCoinsBanked() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,1);
        assertFalse(paymentUnit.getValueOfCoinsBanked()==0);
        paymentUnit.clearCoinsBanked();
        assertEquals(paymentUnit.getValueOfCoinsBanked(),0);
    }

    @Test
    public void testAddOneTypeToBanked() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,5);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),5);
        assertEquals(paymentUnit.getValueOfCoinsBanked(),500);
        assertEquals(paymentUnit.getCurrentTransaction().size(), 0);
    }

    @Test
    public void testAddManyType() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,1);
        paymentUnit.addCoinsToBanked(Coin.DIME,15);
        paymentUnit.addCoinsToBanked(Coin.NICKEL,20);
        paymentUnit.addCoinsToBanked(Coin.QUARTER,2);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),1);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER),2);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),15);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL),20);
        assertEquals(paymentUnit.getValueOfCoinsBanked(),400);
        assertEquals(paymentUnit.getCurrentTransaction().size(),0);
    }

    @Test
    public void testgetNumberOfCoinsBankedOfType() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,1);
        paymentUnit.addCoinsToBanked(Coin.DIME,15);
        paymentUnit.addCoinsToBanked(Coin.NICKEL,20);
        paymentUnit.addCoinsToBanked(Coin.QUARTER,2);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),1);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),15);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL),20);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER),2);

        paymentUnit.clearCoinsBanked();
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER),0);
    }

    @Test
    public void testgetValueOfCoinsBanked(){
        paymentUnit.addCoinsToBanked(Coin.LOONIE,2);
        paymentUnit.addCoinsToBanked(Coin.DIME,15);
        paymentUnit.addCoinsToBanked(Coin.NICKEL,20);
        paymentUnit.addCoinsToBanked(Coin.QUARTER,2);
        assertEquals(paymentUnit.getValueOfCoinsBanked(), 500);

        paymentUnit.clearCoinsBanked();
        assertEquals(paymentUnit.getValueOfCoinsBanked(),0);
    }

    @Test
    public void testinsertOneTypeOnce() {
        paymentUnit.insertCoin(Coin.LOONIE);

        assertEquals(paymentUnit.getCurrentTransaction().size(), 1);
        assertEquals(paymentUnit.getValueOfCoinsInserted(),100);
    }

    @Test
    public void testinsertOneTypeManyTimes() {
        for(int i=0;i<5;i++)
            paymentUnit.insertCoin(Coin.LOONIE);

        assertEquals(paymentUnit.getCurrentTransaction().size(), 5);
        assertEquals(paymentUnit.getValueOfCoinsInserted(),500);
    }

    @Test
    public void testinsertManyTypesOnce() {
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.DIME);
        paymentUnit.insertCoin(Coin.NICKEL);

        assertEquals(paymentUnit.getCurrentTransaction().size(), 4);
        assertEquals(paymentUnit.getValueOfCoinsInserted(),140);
    }

    @Test
    public void testinsertManyTypesManyTimes() {
        for(int i = 0; i < 3; i++)
            paymentUnit.insertCoin(Coin.LOONIE);
        for(int i = 0; i < 2; i++)
            paymentUnit.insertCoin(Coin.QUARTER);
        for(int i = 0; i < 5; i++)
            paymentUnit.insertCoin(Coin.DIME);
        for(int i = 0; i <10; i++)
            paymentUnit.insertCoin(Coin.NICKEL);

        assertEquals(paymentUnit.getCurrentTransaction().size(), 20);
        assertEquals(paymentUnit.getValueOfCoinsInserted(),450);
    }

    @Test
    public void testgetValueOfCoinsInserted() {
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.DIME);
        paymentUnit.insertCoin(Coin.NICKEL);

        assertEquals(paymentUnit.getValueOfCoinsInserted(),140);
    }

    @Test
    public void testInsertOneCancelTransaction() {
        paymentUnit.insertCoin(Coin.LOONIE);
        List<Coin> A = new ArrayList<>();
        A.add(Coin.LOONIE);
        assertEquals(A,paymentUnit.cancelTransaction());

    }

    @Test
    public void testInsertManyCancelTransaction() {
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.DIME);

        List<Coin> A = new ArrayList<>();

        A.add(Coin.LOONIE);
        A.add(Coin.QUARTER);
        A.add(Coin.DIME);

        assertEquals(A,paymentUnit.cancelTransaction());
    }


    @Test
    public void testMakePurchaseNoChange() {
        paymentUnit.insertCoin(Coin.LOONIE);
        List<Coin> A = new ArrayList<>();
        assertEquals(A, paymentUnit.makePurchase(100));
        assertEquals(paymentUnit.makePurchase(100).size(),0);
    }

    @Test
    public void testMakePurchaseWithChangeButCanNot() {
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.DIME);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),0);
        paymentUnit.makePurchase(101);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),1);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),1);
    }

    @Test
    public void testMakePurchaseWithChange1DollarAsChange() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,1);
        for(int i = 0; i < 8; i++) {
            paymentUnit.insertCoin(Coin.QUARTER);
        }
        List<Coin> A = new ArrayList<>();
        A.add(Coin.LOONIE);

        assertEquals(A,paymentUnit.makePurchase(100));
    }

    @Test
    public void testMakePurchaseWithChange4QuarterAs1Dollar() {
        for(int i = 0; i < 8; i++) {
            paymentUnit.insertCoin(Coin.QUARTER);
        }

        List<Coin> A = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            A.add(Coin.QUARTER);
        }

        assertEquals(A,paymentUnit.makePurchase(100));
    }

    @Test
    public void testMakePurchaseWithEveryTypeCoinAsChange() {
        paymentUnit.addCoinsToBanked(Coin.LOONIE,1);
        paymentUnit.addCoinsToBanked(Coin.QUARTER,1);
        paymentUnit.addCoinsToBanked(Coin.DIME,1);
        paymentUnit.addCoinsToBanked(Coin.NICKEL,1);

        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.NICKEL);

        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),1);

        List<Coin> A = new ArrayList<>();
        A.add(Coin.LOONIE);
        A.add(Coin.QUARTER);
        A.add(Coin.DIME);
        A.add(Coin.NICKEL);
        A.add(Coin.NICKEL);

        assertEquals(A,paymentUnit.makePurchase(60));

        assertTrue(paymentUnit.getCurrentTransaction().size() == 0);
    }

    @Test
    public void testMakePurchaseWithChangeManyMoreCoinsAreInsertedThanNeeded() {
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.DIME);
        paymentUnit.insertCoin(Coin.NICKEL);

        List<Coin> A = paymentUnit.makePurchase(0);

        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME),0);
        assertEquals(paymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL),0);

        List<Coin> B = new ArrayList<>();

        B.add(Coin.LOONIE);
        B.add(Coin.QUARTER);
        B.add(Coin.DIME);
        B.add(Coin.NICKEL);

        assertEquals(A,B);
    }

    @Test
    public void testMakePurchaseFinal() {
        List<Coin> A = new ArrayList<>();

        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.DIME);
        paymentUnit.insertCoin(Coin.DIME);
        paymentUnit.insertCoin(Coin.NICKEL);
        paymentUnit.insertCoin(Coin.NICKEL);

        A.add(Coin.LOONIE);
        A.add(Coin.QUARTER);
        A.add(Coin.DIME);
        A.add(Coin.NICKEL);

        assertEquals(A,paymentUnit.makePurchase(140));
    }

    @Test
    public void testMakePurchaseFinal2() {
        List<Coin> A = new ArrayList<>();
        A.clear();
        paymentUnit.insertCoin(Coin.LOONIE);
        paymentUnit.insertCoin(Coin.QUARTER);
        paymentUnit.insertCoin(Coin.QUARTER);
        assertEquals(A,paymentUnit.makePurchase(140));
    }
    //makePurchase: test correct change when many more coins are inserted than needed

}
