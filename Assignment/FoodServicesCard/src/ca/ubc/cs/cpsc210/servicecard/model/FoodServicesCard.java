package ca.ubc.cs.cpsc210.servicecard.model;

import ca.ubc.cs.cpsc210.servicecard.tests.FoodServicesCardTest;

// Represents a card used to purchase food services at a university
public class FoodServicesCard {
    // NOTE TO CPSC 210 STUDENTS: normally, the 'final' keyword would be used in the declaration
    // of constants.  We omit it here to allow us to run tests against your code to
    // check that it works when different values are assigned.
    public static int POINTS_NEEDED_FOR_CASH_BACK = 2000;    // points needed to get cash-back reward
    public static int REWARD_POINTS_PER_CENT_CHARGED = 1;    // points earned for each cent charged to card
    public static int CASH_BACK_REWARD = 10;                 // reward in cents

    // add fields to represent changing properties of a food services card

    private int CURRENT_REWARD_POINT;
    private int CURRENT_BALANCE;



    // REQUIRES: initialBalance >= 0
    // EFFECTS: constructs food service card with given initial balance in cents and zero reward points
    public FoodServicesCard(int initialBalance) {
        this.CURRENT_BALANCE = initialBalance;
        this.CURRENT_REWARD_POINT = 0;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount cents to balance on card
    public void reload(int amount) {
        CURRENT_BALANCE = CURRENT_BALANCE + amount;
    }

    // MODIFIES: this
    // EFFECTS: if there is sufficient balance on the account
    //            - subtracts amount cents from balance
    //            - adds reward points and then
    //                - if eligible, adds cash back reward(s) to account and deducts corresponding reward points
    //            - returns true
    //          otherwise, returns false
    public boolean makePurchase(int amount) {
        if (CURRENT_BALANCE >= amount) {
            CURRENT_BALANCE = CURRENT_BALANCE - amount;
            CURRENT_REWARD_POINT = CURRENT_REWARD_POINT + REWARD_POINTS_PER_CENT_CHARGED * amount;
            int RewardTimes = CURRENT_REWARD_POINT / POINTS_NEEDED_FOR_CASH_BACK;
            if (RewardTimes >= 1){
                CURRENT_BALANCE = CURRENT_BALANCE + CASH_BACK_REWARD * RewardTimes;
                CURRENT_REWARD_POINT = CURRENT_REWARD_POINT - POINTS_NEEDED_FOR_CASH_BACK * RewardTimes;
            }
            return true;
        } else
            return false;
    }

    // EFFECTS: returns remaining balance in cents
    public int getBalance() {
        return CURRENT_BALANCE;
    }

    // EFFECTS: returns number of unused reward points
    public int getRewardPoints() {
        return CURRENT_REWARD_POINT;
    }
}

