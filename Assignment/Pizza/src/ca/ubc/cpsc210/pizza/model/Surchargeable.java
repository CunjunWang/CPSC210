package ca.ubc.cpsc210.pizza.model;

// Represents an extra cost

public interface Surchargeable {

	// Set the extra cost
	// REQUIRES: surcharge >= 0
	// MODIFIES: this (when implemented on class)
	// EFFECTS: surcharge is set to given value
	void setSurcharge(int surcharge);
}
