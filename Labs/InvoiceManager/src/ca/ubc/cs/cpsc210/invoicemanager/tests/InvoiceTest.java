package ca.ubc.cs.cpsc210.invoicemanager.tests;

import ca.ubc.cs.cpsc210.invoicemanager.model.Invoice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Unit tests for the Invoice class
public class InvoiceTest {
    private Invoice testInvoice;

    @Before
    public void runBefore() {
        testInvoice = new Invoice(1, 10, 4, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(14, testInvoice.getAmountOwing());
        assertFalse(testInvoice.isPaidInFull());
    }

    @Test
    public void testMakePayment() {
        testInvoice.makePayment(4);

        assertEquals(10, testInvoice.getAmountOwing());
        assertFalse(testInvoice.isPaidInFull());
    }

    @Test
    public void testPayInFull() {
        testInvoice.makePayment(testInvoice.getAmountOwing());
        assertTrue(testInvoice.isPaidInFull());
    }

}