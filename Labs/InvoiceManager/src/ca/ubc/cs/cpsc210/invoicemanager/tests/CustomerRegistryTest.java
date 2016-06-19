package ca.ubc.cs.cpsc210.invoicemanager.tests;

import ca.ubc.cs.cpsc210.invoicemanager.model.Customer;
import ca.ubc.cs.cpsc210.invoicemanager.model.CustomerRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// Unit tests for customer registry
public class CustomerRegistryTest {
    private CustomerRegistry testCustomerRegistry;

    @Before
    public void runBefore() {
        testCustomerRegistry = CustomerRegistry.getInstance();
        testCustomerRegistry.clearCustomers();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCustomerRegistry.getNumberOfCustomers());
        assertTrue(testCustomerRegistry.getCustomers().isEmpty());
    }

    @Test
    public void testAddCustomers() {
        Customer c1 = new Customer("Smith");
        Customer c2 = new Customer("Wang");

        testCustomerRegistry.addCustomer(c1);
        testCustomerRegistry.addCustomer(c2);

        List<Customer> customers = testCustomerRegistry.getCustomers();
        assertEquals(c1, customers.get(0));
        assertEquals(c2, customers.get(1));
        assertEquals(2, customers.size());
        assertEquals(2, testCustomerRegistry.getNumberOfCustomers());
    }
}