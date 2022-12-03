package com.example.p5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class OrderPizzaActivity extends AppCompatActivity {
    private ArrayList<User> usersList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setting up RecyclerView
        recyclerView = findViewById(R.id.pizzaOptions);
        usersList = new ArrayList<>();
        setUserInfo();
        setAdapter();
    }

    private void setAdapter() {
        recyclerAdapter adapter= new recyclerAdapter(usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setUserInfo() {
        usersList.add(new User("Max"));
        usersList.add(new User("Luke"));
        usersList.add(new User("Professor"));
    }

}