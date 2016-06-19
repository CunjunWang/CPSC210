package ca.ubc.cs.cpsc210.microwave.tests;

import ca.ubc.cs.cpsc210.microwave.model.IMicrowaveOvenStateMachine;
import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenState;
import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenStateMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// unit tests for MicrowaveOvenStateMachine
public class MicrowaveOvenStateMachineTest {
    private IMicrowaveOvenStateMachine testOven;

    @Before
    public void runBefore() {
        testOven = new MicrowaveOvenStateMachine();
    }

    @Test
    public void testXXXXX() {
        // template for tests
    }

    @Test
    public void testIDLE() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(testOven.setTime(5,5),MicrowaveOvenState.PROGRAMMED);
        MicrowaveOvenStateMachine testOven1 = new MicrowaveOvenStateMachine();
        assertEquals(null,testOven1.setPowerLevel(1));
        MicrowaveOvenStateMachine testOven2 = new MicrowaveOvenStateMachine();
        assertEquals(null,testOven2.start());
        MicrowaveOvenStateMachine testOven3 = new MicrowaveOvenStateMachine();
        assertEquals(null,testOven3.cancel());
        MicrowaveOvenStateMachine testOven4 = new MicrowaveOvenStateMachine();
        assertEquals(null,testOven4.pause());
        MicrowaveOvenStateMachine testOven5 = new MicrowaveOvenStateMachine();
        assertEquals(null,testOven5.resume());

    }

    @Test
    public void testPROGRAMMED() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.setTime(5,5);
        assertEquals(null,testOven.setTime(5,5));

        MicrowaveOvenStateMachine testOven1 = new MicrowaveOvenStateMachine();
        testOven1.setTime(5,5);
        assertEquals(MicrowaveOvenState.PROGRAMMED,testOven1.setPowerLevel(1));

        MicrowaveOvenStateMachine testOven2 = new MicrowaveOvenStateMachine();
        testOven2.setTime(5,5);
        assertEquals(MicrowaveOvenState.COOKING,testOven2.start());

        MicrowaveOvenStateMachine testOven3 = new MicrowaveOvenStateMachine();
        testOven3.setTime(5,5);
        assertEquals(MicrowaveOvenState.IDLE,testOven3.cancel());

        MicrowaveOvenStateMachine testOven4 = new MicrowaveOvenStateMachine();
        testOven4.setTime(5,5);
        assertEquals(null,testOven4.pause());

        MicrowaveOvenStateMachine testOven5 = new MicrowaveOvenStateMachine();
        testOven5.setTime(5,5);
        assertEquals(null,testOven5.resume());

    }

    @Test
    public void testCOOKING() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.setTime(5,5);
        testOven.setPowerLevel(5);
        testOven.start();
        assertEquals(null,testOven.setTime(5,5));

        MicrowaveOvenStateMachine testOven1 = new MicrowaveOvenStateMachine();
        testOven1.setTime(5,5);
        testOven1.setPowerLevel(1);
        testOven1.start();
        assertEquals(null,testOven1.setPowerLevel(1));

        MicrowaveOvenStateMachine testOven2 = new MicrowaveOvenStateMachine();
        testOven2.setTime(5,5);
        testOven2.setPowerLevel(1);
        testOven2.start();
        assertEquals(null,testOven1.start());

        MicrowaveOvenStateMachine testOven3 = new MicrowaveOvenStateMachine();
        testOven3.setTime(5,5);
        testOven3.setPowerLevel(1);
        testOven3.start();
        assertEquals(null,testOven3.cancel());

        MicrowaveOvenStateMachine testOven4 = new MicrowaveOvenStateMachine();
        testOven4.setTime(5,5);
        testOven4.setPowerLevel(1);
        testOven4.start();
        assertEquals(MicrowaveOvenState.PAUSED,testOven4.pause());

        MicrowaveOvenStateMachine testOven5 = new MicrowaveOvenStateMachine();
        testOven5.setTime(5,5);
        testOven5.setPowerLevel(1);
        testOven5.start();
        assertEquals(null,testOven5.resume());
    }

    @Test
    public void testPAUSED() {
        assertEquals(MicrowaveOvenState.IDLE,testOven.getCurrentState());
        testOven.setTime(5,5);
        testOven.setPowerLevel(1);
        testOven.start();
        testOven.pause();
        assertEquals(null,testOven.setTime(5,5));

        MicrowaveOvenStateMachine testOven1 = new MicrowaveOvenStateMachine();
        testOven1.setTime(5,5);
        testOven1.setPowerLevel(1);
        testOven1.start();
        testOven1.pause();
        assertEquals(null,testOven1.setPowerLevel(1));

        MicrowaveOvenStateMachine testOven2 = new MicrowaveOvenStateMachine();
        testOven2.setTime(5,5);
        testOven2.setPowerLevel(1);
        testOven2.start();
        testOven2.pause();
        assertEquals(null,testOven2.start());

        MicrowaveOvenStateMachine testOven3 = new MicrowaveOvenStateMachine();
        testOven3.setTime(5,5);
        testOven3.setPowerLevel(1);
        testOven3.start();
        testOven3.pause();
        assertEquals(MicrowaveOvenState.IDLE,testOven3.cancel());

        MicrowaveOvenStateMachine testOven4 = new MicrowaveOvenStateMachine();
        testOven4.setTime(5,5);
        testOven4.setPowerLevel(1);
        testOven4.start();
        testOven4.pause();
        assertEquals(null,testOven4.pause());

        MicrowaveOvenStateMachine testOven5 = new MicrowaveOvenStateMachine();
        testOven5.setTime(5,5);
        testOven5.setPowerLevel(1);
        testOven5.start();
        testOven5.pause();
        assertEquals(MicrowaveOvenState.COOKING,testOven5.resume());
    }

}