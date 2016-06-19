package ca.ubc.cs.cpsc210.snake.tests.model;

import ca.ubc.cs.cpsc210.snake.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// jUnit tests for SnakeGame class
public class SnakeGameTest {
    private SnakeGame testSnakeGame;

    @Before
    public void runBefore() {
        testSnakeGame = new SnakeGame();
    }

    @Test
    public void testIsGameOverMoveRight() {
        setSnakeDirection(Direction.RIGHT);
        Cell snakePosition = testSnakeGame.getSnakeHeadPosition();
        int distanceToRightWall = SnakeGame.BOARD_COLS - snakePosition.getColumn();

        for(int i = 0; i < distanceToRightWall - 1; i++) {
            testSnakeGame.update();
        }

        assertFalse(testSnakeGame.isOver());

        testSnakeGame.update();

        assertTrue(testSnakeGame.isOver());
    }

    @Test
    public void testIsGameOverMoveLeft() {
        setSnakeDirection(Direction.LEFT);
        Cell snakePosition = testSnakeGame.getSnakeHeadPosition();
        int distanceToLeftWall = snakePosition.getColumn();

        for(int i = 0; i < distanceToLeftWall; i++) {
            testSnakeGame.update();
        }

        assertFalse(testSnakeGame.isOver());

        testSnakeGame.update();

        assertTrue(testSnakeGame.isOver());
    }

    @Test
    public void testIsGameOverMoveDown() {
        setSnakeDirection(Direction.DOWN);
        Cell snakePosition = testSnakeGame.getSnakeHeadPosition();
        int distanceToBottomWall = SnakeGame.BOARD_ROWS - snakePosition.getRow();

        for(int i = 0; i < distanceToBottomWall - 1; i++) {
            testSnakeGame.update();
        }

        assertFalse(testSnakeGame.isOver());

        testSnakeGame.update();

        assertTrue(testSnakeGame.isOver());
    }

    @Test
    public void testIsGameOverMoveUp() {
        setSnakeDirection(Direction.UP);

        for(int i = 0; i < 5; i++) {
            testSnakeGame.update();
        }

        assertFalse(testSnakeGame.isOver());

        testSnakeGame.update();

        assertTrue(testSnakeGame.isOver());
    }

    @Test
    public void testRotateSnakeLeft() {
        setSnakeDirection(Direction.UP);
        testSnakeGame.rotateSnakeLeft();
        assertEquals(Direction.LEFT, testSnakeGame.getSnakeDirection());
    }

    @Test
    public void testRotateSnakeRight() {
        setSnakeDirection(Direction.UP);
        testSnakeGame.rotateSnakeRight();
        assertEquals(Direction.RIGHT, testSnakeGame.getSnakeDirection());
    }

    @Test
    public void testSnakeGrowsWhenEating() {
        while(testSnakeGame.getSnakeLength() == 1) {
            eatFood();
        }

        assertTrue(testSnakeGame.getSnakeLength() > 1);
        assertEquals(testSnakeGame.getSnakeLength(), 1 + testSnakeGame.getSnakeBodyPositions().size());
    }

    @Test
    public void testNewFoodCreatedWhenEaten() {
        eatFood();
        assertFalse(testSnakeGame.getFoodPosition().equals(testSnakeGame.getSnakeHeadPosition()));
        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE, testSnakeGame.getFoodNutritionalValue());
    }

    // MODIFIES: this
    // EFFECTS:  move snake to cell containing food so that food is eaten by snake
    private void eatFood() {
        Cell foodPosition = testSnakeGame.getFoodPosition();
        Cell snakePosition = testSnakeGame.getSnakeHeadPosition();
        int rowDiff = foodPosition.getRow() - snakePosition.getRow();
        int colDiff = foodPosition.getColumn() - snakePosition.getColumn();

        if (rowDiff > 0) {
            setSnakeDirection(Direction.DOWN);
            for (int i = 0; i < rowDiff; i++)
                testSnakeGame.update();
        }
        else if (rowDiff < 0) {
            setSnakeDirection(Direction.UP);
            for (int i = 0; i < -rowDiff; i++)
                testSnakeGame.update();
        }

        if (colDiff > 0) {
            setSnakeDirection(Direction.RIGHT);
            for (int i = 0; i < colDiff; i++)
                testSnakeGame.update();
        }
        else if (colDiff < 0) {
            setSnakeDirection(Direction.LEFT);
            for (int i = 0; i < -colDiff; i++)
                testSnakeGame.update();
        }
    }

    // MODIFIES: this
    // EFFECTS:  rotates snake until its direction is d
    private void setSnakeDirection(Direction d) {
        while (testSnakeGame.getSnakeDirection() != d)
            testSnakeGame.rotateSnakeLeft();
    }
}