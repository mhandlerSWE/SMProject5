package com.example.p5;
/**
 * Class for Pizza Factory
 * Initializes methods to make pizza of each type
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public interface PizzaFactory {
    /**
     * Must be able to create a Deluxe Pizza
     * @return
     */
    Pizza createDeluxe();

    /**
     * Must be able to create a Meatzza Pizza
     * @return
     */
    Pizza createMeatzza();

    /**
     * Must be able to create a BBQ Chicken Pizza
     * @return
     */
    Pizza createBBQChicken();

    /**
     * Must be able to create a BuildYourOwn Pizza
     * @return
     */
    Pizza createBuildYourOwn();

}


