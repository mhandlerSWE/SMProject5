package com.example.p5;
/**
 * Class for Build Your Own Pizza
 * Extends Pizza and inherits all data fields and methods from it
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    private static final double SMALL = 8.99;
    private static final double MEDIUM = 10.99;
    private static final double LARGE = 12.99;
    private static final double NOT_FOUND = -1;

    /**
     * Constructor for Build Your Own Pizza
     */
    public BuildYourOwn() {
        super();
    }


    /**
     * Method to get price of pizza
     * @return double of this pizza's price
     */
    @Override
    public double price(){
        double tempPrice = 0.0;
        if(this.getSize() == Size.SMALL) tempPrice += SMALL;
        else if(this.getSize() == Size.MEDIUM) tempPrice += MEDIUM;
        else if(this.getSize() == Size.LARGE) tempPrice += LARGE;
        else {
            tempPrice += 100;
        }

        for(int i = 0; i < this.getToppings().size(); i++) {
            tempPrice += 1.59;
        }
        if(tempPrice < 0) return NOT_FOUND;
        return tempPrice;
    }

    /**
     * method to add toppings to this pizza
     * @param obj
     * @return true if topping was added, false if not
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Topping){
            return super.add(obj);
        }
        return false;
    }


    /**
     * Convert this pizza to string
     * @return String containing this pizza's information
     */
    @Override
    public String toString(){
        String string = "(Build Your Own) - Size: " + this.getSize().toString() + " - Crust: " +
                this.getCrust().toString() + " - Toppings: ";
        for (int i = this.getToppings().size()-1; i >=0  ; i--) {
            string += this.getToppings().get(i).toString();
            if (i != 0){
                string += ", ";
            }
        }

        string += String.format(" - Price : %.2f", this.price());
        return string;
    }

    /**
     * Method to remove topping from pizza
     * @param obj
     * @return True if topping is removed, false if not
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping){
            return super.remove(obj);
        }
        return false;
    }

    /**
     * method to create dupicate of this pizza
     * @return copy of this pizza
     */
    @Override
    public Pizza duplicate(){
        if (this.getCrust().equals(Crust.PAN)){
            PizzaFactory copier = new ChicagoPizza();
            Pizza copy = copier.createBuildYourOwn();
            copy.setSize(this.getSize());
            for (int i = this.getToppings().size() -1; i >= 0; i--){
                copy.add(this.getToppings().get(i));
            }
            return copy;
        }
        else {
            PizzaFactory copier = new NYPizza();
            Pizza copy = copier.createBuildYourOwn();
            copy.setSize(this.getSize());
            for (int i = this.getToppings().size() -1; i >= 0; i--){
                copy.add(this.getToppings().get(i));
            }
            return copy;
        }
    }
}
