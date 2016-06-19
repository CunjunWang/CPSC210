package ca.ubc.cs.cpsc210.microwave.model;

import java.util.List;

// Represents a microwave oven
public class MicrowaveOvenStateMachine implements IMicrowaveOvenStateMachine {
    private static final int SECS_PER_MIN = 60;
    private static final int DEFAULT_POWER_LEVEL = 10;
    private MicrowaveOvenState currentState;
    private int cookingTimeInSeconds;
    private int powerLevel;

    public MicrowaveOvenStateMachine() {
        currentState = MicrowaveOvenState.IDLE;
        cookingTimeInSeconds = 0;       powerLevel = DEFAULT_POWER_LEVEL;
    }

    @Override
    public MicrowaveOvenState getCurrentState() {
        return currentState;
    }

    @Override
    public MicrowaveOvenState setTime(int minutes, int seconds) {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case IDLE:
                cookingTimeInSeconds = minutes * SECS_PER_MIN + seconds;
                transitionState = MicrowaveOvenState.PROGRAMMED;
                currentState = transitionState;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    @Override
    public MicrowaveOvenState setPowerLevel(int powerLevel) {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                this.powerLevel = powerLevel;
                transitionState = MicrowaveOvenState.PROGRAMMED;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;  // state does not change
    }

    @Override
    public MicrowaveOvenState start() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                transitionState = MicrowaveOvenState.COOKING;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    @Override
    public MicrowaveOvenState cancel() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                cookingTimeInSeconds = 0;
                powerLevel = DEFAULT_POWER_LEVEL;
                transitionState = MicrowaveOvenState.IDLE;
                currentState = transitionState;
                break;
            case PAUSED:
                cookingTimeInSeconds = 0;
                powerLevel = DEFAULT_POWER_LEVEL;
                transitionState = MicrowaveOvenState.IDLE;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            default:  // COOKING
                transitionState = null;
        }

        return transitionState;
    }

    @Override
    public MicrowaveOvenState pause() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case COOKING:
                transitionState = MicrowaveOvenState.PAUSED;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    @Override
    public MicrowaveOvenState resume() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PAUSED:
                transitionState = MicrowaveOvenState.COOKING;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            default:  // COOKING
                transitionState = null;
        }

        return transitionState;
    }
}
