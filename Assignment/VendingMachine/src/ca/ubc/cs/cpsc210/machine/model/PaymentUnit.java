package ca.ubc.cs.cpsc210.machine.model;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the payment unit in a vending machine.
 */
public class PaymentUnit {
    private int numLoonies;   // number of loonies banked in machine for making change
    private int numQuarters;  // number of quarters banked in machine for making change
    private int numDimes;     // number of dimes banked in machine for making change
    private int numNickels;   // number of nickels banked in machine for making change
    private List<Coin> currentTransaction;   // list of coins inserted into machine during current transaction

    // EFFECTS: constructs a payment unit with no banked coins and no coins inserted into the machine
    // as part of a payment
    public PaymentUnit() {
        numLoonies = 0;
        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
        currentTransaction = new ArrayList<>();

        //TODO: complete implementation
    }

    // MODIFIES: this
    // EFFECTS: clears all the coins banked in the unit
    public void clearCoinsBanked() {
        numLoonies = 0;
        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
        //TODO: complete implementation
    }

    // REQUIRES: number > 0
    // MODIFIES: this
    // EFFECTS: adds number coins of type c to the banked coins in the unit
    public void addCoinsToBanked(Coin c, int number) {
        if (c.getValue() == 100) {
            numLoonies += number;
        } else if (c.getValue() == 25) {
            numQuarters += number;
        } else if (c.getValue() == 10) {
            numDimes += number;
        } else if (c.getValue() == 5)
            numNickels += number;
        //TODO: complete implementation
    }


    // EFFECTS: returns number of coins banked of the given type
    public int getNumberOfCoinsBankedOfType(Coin c) {
        int number = 0;

        if (c.getValue() == 100)
            number = numLoonies;
        else if (c.getValue() == 25)
            number = numQuarters;
        else if (c.getValue() == 10)
            number = numDimes;
        else if (c.getValue() == 5)
            number = numNickels;

        return number;

        //TODO: complete implementation
    }

    // EFFECTS: returns the total value of all coins banked in the unit
    public int getValueOfCoinsBanked() {
        int totalValue = numLoonies * 100 + numQuarters * 25 + numDimes * 10 + numNickels * 5;
        //TODO: complete implementation
        return totalValue;
    }

    // MODIFIES: this
    // EFFECTS: adds coin c to the unit as a part of a transaction
    public void insertCoin(Coin c) {
        currentTransaction.add(c);

        //TODO: complete implementation
    }

    // EFFECTS: returns value of coins inserted for current transaction
    public int getValueOfCoinsInserted() {
        int payment = 0;

        for (Coin this_coin : currentTransaction) {
            payment += this_coin.getValue();
        }
        return payment;

        //TODO: complete implementation
    }


    // MODIFIES: this
    // EFFECTS: coins inserted for current transaction are cleared; list of coins
    // inserted for current transaction is returned in the order in which they were inserted.
    public List<Coin> cancelTransaction() {
        //TODO: complete implementation
        List<Coin> returnlist = new ArrayList<>();
        for (Coin this_coin : currentTransaction) {
            returnlist.add(this_coin);
        }
        currentTransaction.clear();

        return returnlist;
    }


    public List<Coin> getCurrentTransaction() {
        return currentTransaction;
    }

    // REQUIRES: cost <= total value of coins inserted as part of current transaction
    // MODIFIES: this
    // EFFECTS: adds coins inserted to coins banked in unit and returns list of coins that will be provided as change.
    // Coins of largest possible value are used when determining the change.  Change in full is not guaranteed -
    // will provide only as many coins as are banked in the machine, without going over the amount due.
    public List<Coin> makePurchase(int cost) {

        int change = getValueOfCoinsInserted() - cost;

        List<Coin> ChangeList = new ArrayList<>();

        for (Coin this_coin : currentTransaction) {
            if (this_coin.getValue() == 100) {
                numLoonies++;
            } else if (this_coin.getValue() == 25) {
                numQuarters++;
            } else if (this_coin.getValue() == 10) {
                numDimes++;
            } else if (this_coin.getValue() == 5) {
                numNickels++;
            }
        }

        currentTransaction.clear();

        if (change >= 5) {
            while (change >= 100 && getNumberOfCoinsBankedOfType(Coin.LOONIE) > 0) {
                ChangeList.add(Coin.LOONIE);
                numLoonies = numLoonies - 1;
                change = change - 100;
            }

            while (change >= 25 && getNumberOfCoinsBankedOfType(Coin.QUARTER) > 0) {
                change = change - 25;
                numQuarters = numQuarters - 1;
                ChangeList.add(Coin.QUARTER);
            }

            while (change >= 10 && getNumberOfCoinsBankedOfType(Coin.DIME) > 0) {
                change = change - 10;
                numDimes = numDimes - 1;
                ChangeList.add(Coin.DIME);
            }

            while (change >= 5 && getNumberOfCoinsBankedOfType(Coin.NICKEL) > 0) {
                change = change - 5;
                numNickels = numNickels - 1;
                ChangeList.add(Coin.NICKEL);
            }

            currentTransaction.clear();

            return ChangeList;
        }

        currentTransaction.clear();

        return ChangeList;

        //TODO: complete implementation
    }
}
