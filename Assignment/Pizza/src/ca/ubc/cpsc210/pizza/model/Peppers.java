package ca.ubc.cpsc210.pizza.model;

// Represent peppers as a topping

public class Peppers extends Topping {
    public static final String DESCRIPTOR = "peppers";

	// EFFECTS: The name is set to peppers and cost to given cost
	public Peppers(int cost) {
        super(DESCRIPTOR,cost);
        // TODO: implement constructor
	}

}
