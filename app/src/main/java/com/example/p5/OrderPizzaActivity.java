package com.example.p5;
/**
 * Controller Class for Order Pizza Activity
 * Initalizes and controls Order Pizza Activity, allows user to select type they want
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderPizzaActivity extends AppCompatActivity {
    private ArrayList<PizzaOption> usersList;
    private RecyclerView recyclerView;
    private ImageView logo;
    private recyclerAdapter adapter;
    private int images[] = {R.drawable.deluxe, R.drawable.bbq_chicken, R.drawable.meatzza, R.drawable.byop};
    private Button next;

    private MainActivity parent;

    private StoreOrders storeOrders;
    public static Pizza currentPizza;
    public static Order currentOrder;

    /**
     * Create activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(R.drawable.ru_pizza_logo);
        next = (Button) findViewById(R.id.next);
        next.setEnabled(false);

        this.storeOrders = MainActivity.getStoreOrders();
        this.currentOrder = this.storeOrders.getCurrentOrder();

        recyclerView = findViewById(R.id.pizzaOptions);
        usersList = new ArrayList<>();
        setUserInfo();
        setAdapter();

        // tells new screen to open when next button is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPizza != null) {
                    openSelectSizeView();}
                else{
                    String text = "Please select a type of pizza!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }
        });
    }

    /**
     * set adapter for recycler view
     */
    private void setAdapter() {
        adapter = new recyclerAdapter(usersList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Set up types of pizzas available to the user
     */
    public void setUserInfo() {
        usersList.add(PizzaOption.CHICAGO_DELUXE);
        usersList.add(PizzaOption.CHICAGO_BBQ);
        usersList.add(PizzaOption.CHICAGO_MEATZZA);
        usersList.add(PizzaOption.CHICAGO_BYO);
        usersList.add(PizzaOption.NEW_YORK_DELUXE);
        usersList.add(PizzaOption.NEW_YORK_BBQ);
        usersList.add(PizzaOption.NEW_YORK_MEATZZA);
        usersList.add(PizzaOption.NEW_YORK_BYO);
    }

    /**
     * change picture on display based on selected pizza
     * @param pos
     */
    public void changePic(int pos){

        if(pos > 3){
            pos = pos - 4;
        }
        logo.setImageResource(images[pos]);
        next.setEnabled(true);
    }

    /**
     * Open activity to select sizes and toppings
     */
    public void openSelectSizeView(){
        Intent intent = new Intent(this, SelectSizeAndToppingsActivity.class);
        startActivity(intent);
    }

    /**
     * get current pizza
     * @return
     */
    public Pizza getCurrentPizza() {
        return currentPizza;
    }

    /**
     * set current pizza
     * @param pizza
     */
    public void setCurrentPizza(Pizza pizza){
        this.currentPizza = pizza;
    }
}