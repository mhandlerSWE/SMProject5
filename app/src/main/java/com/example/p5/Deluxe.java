package com.example.p5;
/**
 * Class for Deluxe Pizza
 * Extends Pizza and inherits all data fields and methods from it
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class Deluxe extends Pizza{

    private static final double SMALL = 14.99;
    private static final double MEDIUM = 16.99;
    private static final double LARGE = 18.99;
    private static final double NOT_FOUND = -1;

    /**
     * Constructor for Deluxe Pizza
     */
    public Deluxe() {
        super();
    }

    /**
     * Convert this pizza to string
     * @return String containing this pizza's information
     */
    @Override
    public String toString(){
        String string = "(Deluxe)- Size: " + this.getSize().toString() + " - Crust: " +
                this.getCrust().toString() + " - Toppings: " + this.getToppings().toString() +
                String.format(" - Price : %.2f", this.price());
        return string;
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
     * method to create dupicate of this pizza
     * @return copy of this pizza
     */
    @Override
    public Pizza duplicate(){
        if (this.getCrust().equals(Crust.DEEP_DISH)){
            PizzaFactory copier = new ChicagoPizza();
            Pizza copy = copier.createDeluxe();
            copy.setSize(this.getSize());
            return copy;
        }
        else {
            PizzaFactory copier = new NYPizza();
            Pizza copy = copier.createDeluxe();
            copy.setSize(this.getSize());
            return copy;
        }
    }
}
