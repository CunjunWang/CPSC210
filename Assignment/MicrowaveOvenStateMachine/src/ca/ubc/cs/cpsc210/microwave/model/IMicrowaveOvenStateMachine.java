package ca.ubc.cs.cpsc210.microwave.model;

// Represents a microwave oven state machine
public interface IMicrowaveOvenStateMachine {

    // REQUIRES: minutes >= 0, 0 <= seconds < 60
    // MODIFIES: this
    // EFFECTS: if current state is IDLE, sets the cooking time, transitions to PROGRAMMED state and returns new state;
    //          otherwise returns null
    MicrowaveOvenState setTime(int minutes, int seconds);

    // REQUIRES: 1 <= level <= 10
    // MODIFIES: this
    // EFFECTS: if current state is PROGRAMMED, sets the power level, stays in current state and returns current state;
    //          otherwise returns null
    MicrowaveOvenState setPowerLevel(int powerLevel);

    // MODIFIES: this
    // EFFECTS: if current state is PROGRAMMED, starts cooking, transitions to COOKING state and returns new state;
    //          otherwise returns null
    MicrowaveOvenState start();

    // MODIFIES: this
    // EFFECTS: if current state is PAUSED or PROGRAMMED, sets cooking time to 0, sets power level to default,
    //          transitions to IDLE state and returns new state; otherwise returns null
    MicrowaveOvenState cancel();

    // MODIFIES: this
    // EFFECTS: if current state is COOKING, transitions to PAUSED state and returns new state;
    //          otherwise returns null
    MicrowaveOvenState pause();

    // MODIFIES: this
    // EFFECTS: if current state is PAUSED, transitions to COOKING state and returns new state;
    //          otherwise returns null
    MicrowaveOvenState resume();

    // EFFECTS: returns current state
    MicrowaveOvenState getCurrentState();
}
