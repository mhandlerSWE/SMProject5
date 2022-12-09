package com.example.p5;
/**
 * Enum class for Pizza options
 * Defines each type and assigns corresponding string to it
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.io.Serializable;

public enum PizzaOption implements Serializable {
    CHICAGO_DELUXE("Chicago Deluxe"),
    CHICAGO_BBQ("Chicago BBQ Chicken"),
    CHICAGO_MEATZZA("Chicago Meatzza"),
    CHICAGO_BYO("Chicago Build Your Own"),
    NEW_YORK_DELUXE("New York Deluxe"),
    NEW_YORK_BBQ("New York BBQ Chicken"),
    NEW_YORK_MEATZZA("New York Meatzza"),
    NEW_YORK_BYO("New York Build Your Own");
    private String type;
    private boolean isChecked = false;

    /**
     * Constructor for PizzaOption
     * @param type
     */
    PizzaOption(String type) {
        this.type = type;
    }

    /**
     * Get string of current type
     * @return string of current type
     */
    public String getType() {
        return this.type;
    }

}
