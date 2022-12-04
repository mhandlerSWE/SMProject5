package com.example.p5;
/**
 * Class for Pizza Size
 * Three possible sizes; each contains string of its name
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private String size;

    /**
     * Returns the size in String format
     * @param size
     */
    Size(String size){
        this.size = size;
    }
}
