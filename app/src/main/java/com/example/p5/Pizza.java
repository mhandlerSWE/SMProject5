package com.example.p5;
/**
 * Abstract class for Pizza
 * Implements customizable, defines instance variables for all types of pizza
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * Constructor for the Pizza Class
     */
    public Pizza(){
        this.toppings = new ArrayList<Topping>();
    }

    /**
     * Adds a topping to the pizza
     * @param obj
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Topping){
            Topping toBeAdded = (Topping)obj;
            this.toppings.add(toBeAdded);
            return true;
        }
        return false;
    }

    /**
     * Removes a topping from the pizza
     * @param obj
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping){
            Topping toBeRemoved = (Topping)obj;
            for (int i = 0; i < toppings.size(); i++){
                if (toppings.get(i).equals(toBeRemoved)){
                    toppings.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the details about the pizza in a String format
     * @return
     */
    public String toString(){
        String string = "Size: " + this.size.toString() + "\nCrust: " + this.crust.toString() + "\n Toppings: ";
        for (int i = 0; i < this.toppings.size(); i++){
            string += this.toppings.get(i).toString();
            string += "---" + i + "---";
            while (i < this.toppings.size()-1){
                string += ", ";
            }
        }
        return string;
    }

    /**
     * Duplicates the pizza
     * @return
     */
    public Pizza duplicate(){
        return this;
    }

    /**
     * Returns the toppings in a pizza
     * @return
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * Adds a topping to the toppings ArrayList
     * @param topping
     */
    public void addToToppings(Topping topping){
        this.toppings.add(topping);
    }

    /**
     * Returns the crust of the pizza
     * @return
     */
    public Crust getCrust(){
        return this.crust;
    }

    /**
     * Returns the size of the pizza
     * @return
     */
    public Size getSize(){
        return this.size;
    }

    /**
     * Sets the toppings instance variable to a specified group of toppings
     * @param toppings
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Sets the crust to a specific crust
     * @param crust
     */
    public void setCrust(Crust crust){
        this.crust = crust;
    }

    /**
     * Specifically sets the size of the pizza
     * @param size
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Clears all the toppings on the pizza
     */
    public void reset(){
        for (int i = 0; i < toppings.size(); i++){
            this.toppings.remove(i);
        }
        this.crust = null;
        this.size = null;
    }
}

