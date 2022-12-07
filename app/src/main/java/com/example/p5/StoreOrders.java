package com.example.p5;
/**
 * Class for all Orders of Pizza
 * Implements Customizable and defines its methods, keeps track of all orders that have been placed
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import java.util.ArrayList;

public class StoreOrders implements Customizable {
    private ArrayList<Order> storeOrders;
    private int numOrders;
    private Order currentOrder;

    /**
     * Constructor for the Store Orders class
     */
    public StoreOrders(){
        this.numOrders = 0;
        this.storeOrders = new ArrayList<Order>();
        this.newOrder(storeOrders.size());
        this.numOrders++;
    }

    /**
     * Returns the number of orders placed at the store
     * @return
     */
    public int getNumOrders() {
        return this.numOrders;
    }

    /**
     * Returns the current order
     * @return
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Adds an order to store orders
     * @param obj
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order){
            Order toBeAdded = (Order)obj;
            storeOrders.add(toBeAdded);
            numOrders++;
            return true;
        }
        else return false;
    }

    /**
     * Removes an order from store orders
     * @param obj
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order){
            Order toBeRemoved = (Order)obj;
            for(int i = 0; i < storeOrders.size(); i++){
                if (toBeRemoved.equals(storeOrders.indexOf(i))){
                    storeOrders.remove(i);
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Returns a list of all store orders
     * @return
     */
    public ArrayList<Order> getList(){return this.storeOrders;}

    public Order getOrder(int index){
        for(int i = 0; i < storeOrders.size(); i++){
            if (i == index){
                return storeOrders.get(index);
            }
        }
        return null;
    }

    /**
     * Increases the amount of orders by 1
     */
    public void incrementOrders() {
        numOrders++;
    }

    /**
     * Creates a new order with order number i
     * @param i Order Number
     * @return
     */
    public Order newOrder(int i){
        Order newOrder = new Order(i);
        storeOrders.add(newOrder);
        this.currentOrder = newOrder;
        return newOrder;
    }

}

