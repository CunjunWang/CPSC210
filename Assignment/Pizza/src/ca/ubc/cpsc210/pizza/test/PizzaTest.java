package ca.ubc.cpsc210.pizza.test;

import static org.junit.Assert.*;

import ca.ubc.cpsc210.pizza.model.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

// jUnit tests for the Pizza class
public class PizzaTest {
    private Pizza testPizza;

    @Before
    public void runBefore() {
        testPizza = new Pizza();
    }

    @Test
    public void testEmptyPizzaCost() {
        assertEquals(Pizza.CRUSTCOST, testPizza.totalCost());
    }

    @Test
    public void testEmptyPizzaName() {
        assertEquals(Crust.DESCRIPTOR, testPizza.ingredientsAsString());
    }

    @Test
    public void testPizzaWithJustSauceDifferingCostsOfSauce() {
        testPizzaWithJustSauce(2);
        // Need a new testPizza between the two cost tests
        testPizza = new Pizza();
        testPizzaWithJustSauce(10);
    }

    // A helper method for testing
    private void testPizzaWithJustSauce(int costOfSauce) {
        int costOfEmptyPizza = testPizza.totalCost();
        Sauce sauce = new Sauce(costOfSauce);
        testPizza.addIngredient(sauce);
        assertEquals(costOfEmptyPizza + costOfSauce, testPizza.totalCost());
        assertEquals(Crust.DESCRIPTOR + Pizza.INGREDIENT_DESCRIPTOR
                + Sauce.DESCRIPTOR, testPizza.ingredientsAsString());
    }

    @Test
    public void testPizzaWithSauceAndCheeseDifferingCostsAndNames() {
        Sauce sauce = new Sauce(5);
        testPizza.addIngredient(sauce);
        Cheese cheese = new Cheese("cheddar", 7);
        testPizza.addIngredient(cheese);
        assertEquals(Pizza.CRUSTCOST + 5 + 7, testPizza.totalCost());
        assertEquals(Crust.DESCRIPTOR + Pizza.INGREDIENT_DESCRIPTOR + Sauce.DESCRIPTOR + Pizza.INGREDIENT_DESCRIPTOR +
                        "cheddar " + Cheese.DESCRIPTOR,
                testPizza.ingredientsAsString());
    }

    @Test
    public void testPizzaWithSauceCheeseAndOneTopping() {
        int toppingCost = 3;
        List<Topping> toppings = new ArrayList<>();
        Topping topping = new Tomatoes(toppingCost);
        toppings.add(topping);

        testPizzaWithSauceCheeseAndToppings(5, 10, "feta", toppings, toppingCost, 2);
    }

    @Test
    public void testPizzaWithSauceCheeseAndManyToppings() {
        int toppingCost = 5;
        List<Topping> toppings = new ArrayList<>();
        Topping tomatoes = new Tomatoes(toppingCost);
        toppings.add(tomatoes);
        Topping peppers = new Peppers(toppingCost);
        toppings.add(peppers);

        testPizzaWithSauceCheeseAndToppings(5, 10, "feta", toppings, toppingCost, 2);
    }



    // A helper method for testing
    private void testPizzaWithSauceCheeseAndToppings(int costOfSauce,
                                                     int costOfCheese, String cheeseName,
                                                     List<Topping> toppings, int toppingCost, int surchargeCost) {
        Sauce sauce = new Sauce(costOfSauce);
        testPizza.addIngredient(sauce);
        Cheese cheese = new Cheese(cheeseName, costOfCheese);
        cheese.setCost(costOfCheese);
        testPizza.addIngredient(cheese);

        int totalToppingCost = 0;
        String toppingNames = "";

        for (Topping next : toppings) {
            next.setSurcharge(surchargeCost);
            next.setCost(toppingCost);
            testPizza.addIngredient(next);
            totalToppingCost += surchargeCost + toppingCost;
            toppingNames += Pizza.INGREDIENT_DESCRIPTOR + next.getName();
        }

        assertEquals(Pizza.CRUSTCOST + costOfSauce + costOfCheese + totalToppingCost,
                testPizza.totalCost());
        assertEquals("crust" + Pizza.INGREDIENT_DESCRIPTOR + Sauce.DESCRIPTOR + Pizza.INGREDIENT_DESCRIPTOR
                        + cheeseName + " " + Cheese.DESCRIPTOR + toppingNames,
                testPizza.ingredientsAsString());
    }

}
