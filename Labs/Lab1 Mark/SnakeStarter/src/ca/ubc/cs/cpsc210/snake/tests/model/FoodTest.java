package ca.ubc.cs.cpsc210.snake.tests.model;

import ca.ubc.cs.cpsc210.snake.model.Cell;
import ca.ubc.cs.cpsc210.snake.model.Food;
import ca.ubc.cs.cpsc210.snake.model.SnakeGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// jUnit tests for Food class
public class FoodTest {
    private Food testFood;
    private Food testFood2;

    @Before
    public void runBefore() {
        testFood = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testTemplate() {
        // use this as a template for designing your tests
    }

    @Test
    public void testConstructor1() {
        assertEquals(testFood.getNutritionalValue(),Food.INITIAL_NUTRITIONAL_VALUE);
    }

    @Test
    public void testConstructor2() {
        testFood2 = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2), 10);
        assertEquals(testFood2.getNutritionalValue(),10);
        assertEquals(testFood2.getPosition(),new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testGetPosition() {
        assertEquals(testFood.getPosition(),new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testDecayOnce() {
        testFood.decay();
        assertEquals( Food.INITIAL_NUTRITIONAL_VALUE - Food.DECAY_AMOUNT , testFood.getNutritionalValue());
    }

    @Test
    public void testDecayManyTimes() {
        int NUM_DECAYS = (Food.INITIAL_NUTRITIONAL_VALUE / 2) / Food.DECAY_AMOUNT;

        for( int count = 0; count < NUM_DECAYS; count++){
            testFood.decay();
        }

        assertEquals( Food.INITIAL_NUTRITIONAL_VALUE - NUM_DECAYS * Food.DECAY_AMOUNT , testFood.getNutritionalValue());
    }

    @Test
    public void testNutritionMinimum() {
        while(testFood.getNutritionalValue() > 0){
            testFood.decay();
        }

        assertEquals(testFood.getNutritionalValue(), 0);

        testFood.decay();

        assertEquals(testFood.getNutritionalValue(), 0);
    }
}