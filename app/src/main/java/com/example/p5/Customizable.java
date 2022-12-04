package com.example.p5;
/**
 * Class for customizable interface
 * Initalizes methods to add and remove
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
public interface Customizable<E>{
    /**
     * Is able to add an object
     * @param obj
     * @return
     */
    boolean add (Object obj);

    /**
     * Is able to remove an object
     * @param obj
     * @return
     */
    boolean remove(Object obj);
}
