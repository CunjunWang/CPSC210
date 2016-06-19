package ca.ubc.cpsc210.pizza.model;

import java.util.ArrayList;
import java.util.List;

// Represents a pizza

public class Pizza {
    public static final int CRUSTCOST = 5;
    public static final String INGREDIENT_DESCRIPTOR = " topped by ";

	// TODO: declare a field to store the crust (every pizza must have one crust)
    protected Crust Crust;

	// TODO: declare ONE field to store the sauce, cheese, tomatoes, peppers and any future ingredients and toppings
    protected List<Ingredient> Ingredients;

	// EFFECTS: pizza has a crust and empty list of ingredients
	public Pizza() {
        Crust = new Crust(CRUSTCOST);
        Ingredients = new ArrayList<>();
        // TODO: implement constructor
	}

	// REQUIRES: newIngredient is not null
	// MODIFIES: this
	// EFFECTS: newIngredient is added to end of list of ingredients
	public void addIngredient(Ingredient newIngredient) {
        Ingredients.add(newIngredient);
        // TODO: implement method
	}

	// EFFECTS: the returned string starts with CRUST_DESCRIPTOR followed by
	// all ingredients in the order in which they were added,
    // with INGREDIENT_DESCRIPTOR between each ingredient
	public String ingredientsAsString() {
        String ReturnString;
        ReturnString = Crust.DESCRIPTOR;
        List<Ingredient> NewList = new ArrayList<>();

        for(Ingredient this_ingredient : Ingredients){
            NewList.add(this_ingredient);
        }
        for(Ingredient this_ingredient : NewList){
            ReturnString = ReturnString + INGREDIENT_DESCRIPTOR + this_ingredient.getName();
        }
        return ReturnString;
        // TODO: implement method
        // return null;   // stub
	}

    // EFFECTS: Return the total cost of the pizza with crust and
	// all ingredients. Total cost >= CRUSTCOST.
	public int totalCost() {
        int TotalCost = CRUSTCOST;
        for(Ingredient this_ingredient : Ingredients){
            TotalCost = TotalCost + this_ingredient.getCost();
        }
        return TotalCost;
        // TODO: implement method
        // return 0;   // stub
	}
}
