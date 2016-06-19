package ca.ubc.cpsc210.pizza.model;

// Represents the sauce for the pizza

public class Sauce extends Topping {
    public static final String DESCRIPTOR = "sauce";

	// EFFECTS: The name is set to sauce and cost to given cost
	public Sauce(int cost) {
        super(DESCRIPTOR,cost);
        // TODO: implement constructor
	}
}
