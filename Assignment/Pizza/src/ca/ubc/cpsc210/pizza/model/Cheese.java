package ca.ubc.cpsc210.pizza.model;

// Represent the cheese for a pizza

public class Cheese extends Ingredient {
    public static final String DESCRIPTOR = "cheese";

	// EFFECTS: name is set to DESCRIPTOR, cost is set to given cost
    // and surcharge is set to 0
	public Cheese(int cost) {
        super(DESCRIPTOR,cost);
        // TODO: implement constructor
	}

    // EFFECTS: name is set to cheeseName + " " + DESCRIPTOR, cost is set to given cost
    // and surcharge is set to 0
    public Cheese(String cheeseName, int cost) {
        super(cheeseName + " " + DESCRIPTOR,cost);
        // TODO: implement constructor
    }
}
