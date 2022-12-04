package com.example.p5;
/**
 * Class for Pizza Crust
 * Six types in total; each contains string of its name
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */

public enum Crust {
    DEEP_DISH("Deep Dish"),
    PAN("Pan"),
    STUFFED("Stuffed"),
    BROOKLYN("Brooklyn"),
    THIN("Thin"),
    HAND_TOSSED("Hand Tossed");

    private String name;

    /**
     * Constuctor for Crust
     * @param name name of Crust
     */
    Crust(String name){
        this.name = name;
    }

    /**
     * Returns Crust object's only instance variable, it's name
     * @return Crust name
     */
    public String toString(){
        return this.name;
    }
}
