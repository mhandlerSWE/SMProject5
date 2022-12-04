package com.example.p5;
/**
 * Class for New York Pizza
 * Implements PizzaFactory, defines its methods
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class NYPizza implements PizzaFactory{

    /**
     * Creates a Deluxe Pizza
     * @return
     */
    public Pizza createDeluxe(){
        Crust crust = Crust.BROOKLYN;
        ArrayList<Topping> toppings = Topping.deluxe();
        Pizza pizza = new Deluxe();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Creates a BBQ Chicken Pizza
     * @return
     */
    public Pizza createBBQChicken(){
        Crust crust = Crust.THIN;
        ArrayList<Topping> toppings = Topping.BBQChicken();
        Pizza pizza = new BBQChicken();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Create a Meatzza Pizza
     * @return
     */
    public Pizza createMeatzza(){
        Crust crust = Crust.HAND_TOSSED;
        ArrayList<Topping> toppings = Topping.Meatzza();
        Pizza pizza = new Meatzza();
        pizza.add(toppings);
        pizza.setCrust(crust);
        return pizza;
    }

    /**
     * Creates a BYO Pizza
     * @return
     */
    public Pizza createBuildYourOwn(){
        Crust crust = Crust.HAND_TOSSED;
        Pizza pizza = new BuildYourOwn();
        pizza.add(Topping.BuildYourOwn());
        pizza.setCrust(crust);
        return pizza;
    }
}
