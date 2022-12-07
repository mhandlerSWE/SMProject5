package com.example.p5;
/**
 * Class for current order
 * Keeps track of pizzas, toppings, price, and order number
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */

import java.util.ArrayList;

public class Order implements Customizable{

    private MainActivity main;
    private ArrayList<Pizza> pizzas;
    private double price;
    private double tax;
    private double total;
    private int orderNumber;
    private boolean placed;
    private int orders;

    private static int numItems = 0;

    private static final double TAX = 0.06625;

    /**
     * Creates an order object with a specific order number
     * @param i Order Number
     */
    public Order(int i){
        this.price = 0;
        this.orderNumber = i;
        orders++;
        this.placed = false;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Adds a pizza to the order, implementing the Customizable interface
     * @param obj
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza){
            Pizza toBeAdded = (Pizza)obj;
            pizzas.add(toBeAdded);
            numItems++;
            return true;
        }
        else return false;
    }


    /**
     * Removes a Pizza from the order, implementing the Customizable interface
     * @param obj
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza){
            Pizza toBeRemoved = (Pizza)obj;
            for(int i = 0; i < pizzas.size(); i++){
                if (toBeRemoved.equals(pizzas.get(i))){
                    changePrice(pizzas.get(i).price());
                    pizzas.remove(i);
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Updates the Price
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
        this.tax = this.price * TAX;
        this.total = this.price + this.tax;
    }

    /**
     * Changes price by specific amount
     * @param amount
     */
    public void changePrice(double amount){
        this.price += amount;
        this.tax = this.price * TAX;
        this.total = this.price + this.tax;
    }

    /**
     * Lowers price by specific amount
     * @param amount
     */
    public void subtractPrice(double amount){
        this.price -= amount;
        this.tax = this.price * TAX;
        this.total = this.price + this.tax;
    }

    /**
     * Set status of order being place to true
     */
    public void setStatus(){
        this.placed = true;
    }

    /**
     * Returns the a list of pizza's in the current order
     * @return
     */
    public ArrayList<Pizza> getList() {return this.pizzas;}

    public Pizza getPizza(int index) {
        if(pizzas.size() == 0) {
            return null;
        }
        return pizzas.get(index);
    }

    /**
     * Helper method that returns the pizzas in an observable list format so that they can be easily processed by a List View
     * @return
     */
    public ArrayList<String> returnAsList() {
        ArrayList<String> pizzasForDisplay = new ArrayList<>();
        for(int i = 0; i < pizzas.size(); i++) {
            pizzasForDisplay.add(pizzas.get(i).toString());
        }
        return pizzasForDisplay;
    }

    /**
     * Returns the status of whether an order has been placed yet or not
     * @return
     */
    public boolean getStatus(){
        return this.placed;
    }

    /**
     * Returns the price of an order
     * @return
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Returns the tax of an order
     * @return
     */
    public double getTax(){
        return this.tax;
    }

    /**
     * Returns the total of an order
     * @return
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Returns whether or not there are no pizzas in an order
     * @return
     */
    public boolean isEmpty(){
        if(this.pizzas.isEmpty() || this.pizzas == null){
            return true;
        }
        else return false;
    }

    /**
     * Removes all pizzas from an order
     */
    public void clearOrder(){
        for (int i = this.pizzas.size()-1; i >= 0; i--){
            this.pizzas.remove(i);
        }
        this.price = 0.0;
        this.tax = 0.0;
        this.total = 0.0;
        this.placed = false;
    }

    /**
     * Removes a single pizza from an order
     * @param index Index of pizza to be removed
     */
    public void removePizza(int index){
        this.pizzas.remove(index);
    }

    /**
     * Returns order number
     * @return
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * Returns the number of orders in store orders
     * @return
     */
    public int getNumOrders(){return this.orders;}

    public int getNumItems(){return this.getNumItems();}

    /**
     * Returns the pizza at index 0
     * @return
     */
    public Pizza getCurrentPizza(){return this.pizzas.get(0);}
}

