package com.example.p5;
/**
 * Class for BBQ Chicken Pizza
 * Extends Pizza and inherits all data fields and methods from it
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class BBQChicken extends Pizza{

    private static final double SMALL = 13.99;
    private static final double MEDIUM = 15.99;
    private static final double LARGE = 17.99;
    private static final double NOT_FOUND = -1;

    /**
     * Constructor for BBQ Chicken Pizza
     */
    public BBQChicken() {
        super();
    }

    /**
     * Method to get price of pizza
     * @return double of this pizza's price
     */
    @Override
    public double price(){
        if(this.getSize().equals(Size.SMALL)){
            return SMALL;
        }
        else if (this.getSize().equals(Size.MEDIUM)){
            return MEDIUM;
        }
        else if (this.getSize().equals(Size.LARGE)){
            return LARGE;
        }
        else return NOT_FOUND;
    }

    /**
     * method to add toppings to this pizza
     * @param obj
     * @return true if topping was added, false if not
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof ArrayList){
            ArrayList<Topping> toppings = (ArrayList<Topping>) obj;
            for (int i = 0; i < toppings.size(); i++) {
                super.add(toppings.get(i));
            }
            return true;
        }
        return false;
    }

    /**
     * Method to remove topping from pizza
     * @param obj
     * @return false, because no topppings can be removed
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * Convert this pizza to string
     * @return String containing this pizza's information
     */
    @Override
    public String toString(){
        String string = "(BBQ Chicken) - Size: " + this.getSize().toString() + " - Crust: " +
                        this.getCrust().toString() + " - Toppings: " + this.getToppings().toString() +
                        String.format(" - Price : %.2f", this.price());
        return string;
    }

    /**
     * method to create dupicate of this pizza
     * @return copy of this pizza
     */
    @Override
    public Pizza duplicate(){
        if (this.getCrust().equals(Crust.PAN)){
            PizzaFactory copier = new ChicagoPizza();
            Pizza copy = copier.createBBQChicken();
            copy.setSize(this.getSize());
            return copy;
        }
        else {
            PizzaFactory copier = new NYPizza();
            Pizza copy = copier.createBBQChicken();
            copy.setSize(this.getSize());
            return copy;
        }
    }
}
