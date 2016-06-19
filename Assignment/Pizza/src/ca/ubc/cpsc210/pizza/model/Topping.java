package ca.ubc.cpsc210.pizza.model;

// represents a topping on a pizza
public abstract class Topping extends Ingredient implements Surchargeable {
    private int surcharge;

    // EFFECTS: topping has given name, cost and the surcharge is zero
    public Topping(String name, int cost) {
        super(name,cost);
        surcharge = 0;
        // TODO: implement constructor
    }

    @Override
    public void setSurcharge(int surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public int getCost() {
        return super.getCost() + surcharge;
    }


    // TODO: override getCost so that surcharge is added to basic cost
}
