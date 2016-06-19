package ca.ubc.cpsc210.pizza.model;

// Represents tomatoes as a topping
public class Tomatoes extends Topping {
    public static final String DESCRIPTOR = "tomatoes";

	// EFFECTS: name is set to tomatoes and cost to given cost
	public Tomatoes(int cost) {
        super(DESCRIPTOR,cost);
        // TODO: implement constructor
	}
}
