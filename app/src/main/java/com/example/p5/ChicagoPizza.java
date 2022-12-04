package com.example.p5;
/**
 * Class for Chicago Pizza
 * Implements PizzaFactory, defines its methods
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class ChicagoPizza implements PizzaFactory{
    /**
     * Create a Deluxe Pizza
     * @return Deluxe
     */
    public Pizza createDeluxe(){
        Crust crust = Crust.DEEP_DISH;
        ArrayList<Topping> toppings = Topping.deluxe();
        Pizza pizza = new Deluxe();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Creates a BBQ Chicken Pizza
     * @return BBQChicken
     */
    public Pizza createBBQChicken(){
        Crust crust = Crust.PAN;
        ArrayList<Topping> toppings = Topping.BBQChicken();
        Pizza pizza = new BBQChicken();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Creates a Meatzza Pizza
     * @return Meatzza
     */
    public Pizza createMeatzza(){
        Crust crust = Crust.STUFFED;
        ArrayList<Topping> toppings = Topping.Meatzza();
        Pizza pizza = new Meatzza();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Creates a Build Your Own
     * @return BuildYourOwn
     */
    public Pizza createBuildYourOwn(){
        Crust crust = Crust.PAN;
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(crust);
        return pizza;
    }
}

