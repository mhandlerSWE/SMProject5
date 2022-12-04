package com.example.p5;
/**
 * Class for Pizza Toppings
 * Thirteen types in total; each contains string of its name
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQ_CHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    KALE("Kale"),
    EGGPLANT("Eggplant"),
    EGG("Egg");
    private String name;

    /**
     * Constructor for the toppin enum class
     * @param name
     */
    Topping(String name){
        this.name = name;
    }

    /**
     * Returns the name of the topping as a String
     * @return
     */
    public String toString(){
        return this.name;
    }

    /**
     * Checks if the current topping is equal to the passed in Topping t
     * @param t
     * @return
     */
    public boolean equals(Topping t) {
        if(this.name.equals(t.toString())) return true;
        return false;
    }

    /**
     * Returns the toppings that go on a Deluxe Pizza
     * @return
     */
    public static ArrayList<Topping> deluxe(){
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        toppings.add(SAUSAGE);
        toppings.add(PEPPERONI);
        toppings.add(GREEN_PEPPER);
        toppings.add(ONION);
        toppings.add(MUSHROOM);
        return toppings;
    }

    /**
     * Returns the toppings that go on a BBQChicken Pizza
     * @return
     */
    public static ArrayList<Topping> BBQChicken(){
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        toppings.add(BBQ_CHICKEN);
        toppings.add(GREEN_PEPPER);
        toppings.add(PROVOLONE);
        toppings.add(CHEDDAR);
        return toppings;
    }

    /**
     * Returns the toppings that go on a Meatzza Pizza
     * @return
     */
    public static ArrayList<Topping> Meatzza(){
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        toppings.add(SAUSAGE);
        toppings.add(PEPPERONI);
        toppings.add(BEEF);
        toppings.add(HAM);
        return toppings;
    }

    /**
     * Returns the toppings that go on a BuildYourOwn Pizza
     * @return
     */
    public static ArrayList<Topping> BuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        return toppings;
    }
}
