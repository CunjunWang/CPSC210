package ca.ubc.cpsc210.pizza.model;

// Represents chargeable items that have an associated cost
public interface Chargeable {

	// REQUIRES: cost >= 0
	// MODFIIES: this (when implemented by a class)
	// EFFECTS: the item has the given cost
	void setCost(int cost);

	// EFFECTS: returns the cost of the item and cost >= 0
	int getCost();
}
