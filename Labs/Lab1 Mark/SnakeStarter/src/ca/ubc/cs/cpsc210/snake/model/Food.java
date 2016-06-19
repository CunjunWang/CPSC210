package ca.ubc.cs.cpsc210.snake.model;

// Represents food.
public class Food {
    public static final int DECAY_AMOUNT = 10;
    public static final int INITIAL_NUTRITIONAL_VALUE = 100;

    // the position of food
    private Cell position;
    // the nutritional value of food
    private int nutritionalValue;

    // EFFECTS: food has INITIAL_NUTRITIONAL_VALUE and is located at given cell
    public Food(Cell position) {

        this.position = position;
        this.nutritionalValue = INITIAL_NUTRITIONAL_VALUE;

    }

    // EFFECTS: food has given nutritional value and is located at given cell
    public Food(Cell position, int nutritionalValue) {

        this.position = position;
        this.nutritionalValue = nutritionalValue;
    }

    public Cell getPosition() {
        return position;
    }

    // MODIFIES: this
    // EFFECTS:  reduces nutritional value of food by DECAY_AMOUNT while maintaining a minimum
    //           nutritional value of zero
    public void decay() {
        nutritionalValue = nutritionalValue - DECAY_AMOUNT;
        handleBoundary();
    }

    public void handleBoundary(){
        if(nutritionalValue < 0){
            nutritionalValue = 0;
        }
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }
}
