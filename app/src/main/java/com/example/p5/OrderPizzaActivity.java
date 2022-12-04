package com.example.p5;

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

import java.util.ArrayList;

public class OrderPizzaActivity extends AppCompatActivity {
    private ArrayList<PizzaOption> usersList;
    private RecyclerView recyclerView;
    private ImageView logo;
    private recyclerAdapter adapter;
    private int images[] = {R.drawable.deluxe, R.drawable.bbq_chicken, R.drawable.meatzza, R.drawable.byop};
    private Button next;

    private MainActivity parent;

    private Pizza currentPizza;
    private Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(R.drawable.ru_pizza_logo);
        //next.setEnabled(false);

        // setting up RecyclerView
        recyclerView = findViewById(R.id.pizzaOptions);
        usersList = new ArrayList<>();
        setUserInfo();
        setAdapter();
    }

    private void setAdapter() {
        adapter = new recyclerAdapter(usersList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

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

    public void changePic(int pos){

        if (pos > 3){
            pos = pos - 4;
            logo.setImageResource(images[pos]);
        }
        else{
            logo.setImageResource(images[pos]);
        }
        next.setEnabled(true);
    }

    public void next(){
        if(adapter.getCheckedPosition() == -1){
            return;
        }

        Intent intent = new Intent(this, selectSizeActivity.class);
        startActivity(intent);
    }

    /**
     * create deluxe NY pizza
     */
    public void nyDeluxe(){
        PizzaFactory pizzaFactory = new NYPizza();
        this.currentPizza = pizzaFactory.createDeluxe();
    }

    /**
     * create bbq chicken NY pizza
     */
    public void nyBBQ(){
        PizzaFactory pizzaFactory = new NYPizza();
        this.currentPizza = pizzaFactory.createBBQChicken();

    }

    /**
     * create NY meatzza
     */
    public void nyMeatzza(){
        PizzaFactory pizzaFactory = new NYPizza();
        this.currentPizza = pizzaFactory.createMeatzza();

    }

    /**
     * create NY build your own
     */
    public void nyBuildYourOwn(){
        PizzaFactory pizzaFactory = new NYPizza();
        this.currentPizza = pizzaFactory.createBuildYourOwn();

    }

    /**
     * create deluxe Chicago pizza
     */
    public void chiDeluxe(){
        PizzaFactory pizzaFactory = new ChicagoPizza();
        this.currentPizza = pizzaFactory.createDeluxe();
    }

    /**
     * create bbq chicken Chicago pizza
     */
    public void chiBBQ(){
        PizzaFactory pizzaFactory = new ChicagoPizza();
        this.currentPizza = pizzaFactory.createBBQChicken();

    }

    /**
     * create Chicago meatzza
     */
    public void chiMeatzza(){
        PizzaFactory pizzaFactory = new ChicagoPizza();
        this.currentPizza = pizzaFactory.createMeatzza();

    }

    /**
     * create Chicago build your own
     */
    public void chiBuildYourOwn(){
        PizzaFactory pizzaFactory = new ChicagoPizza();
        this.currentPizza = pizzaFactory.createBuildYourOwn();

    }
}