package com.example.p5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    public static Order currentOrder;
    private String[] pizzas;

    private ListView orderListView;

    private Button placeOrder, clearOrder, removeSelected;

    ArrayAdapter<String> arr;


    private boolean isSelected;

    private int selectedPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isSelected = false;

        placeOrder = (Button) findViewById(R.id.placeOrder);
        clearOrder = (Button) findViewById(R.id.clearOrder);
        removeSelected = (Button) findViewById(R.id.removeSelected);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
        clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOrder();
            }
        });
        removeSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePizza();
            }
        });

        this.storeOrders = MainActivity.getStoreOrders();
        this.currentOrder = this.storeOrders.getCurrentOrder();

        this.setUpPizzas();

        orderListView = findViewById(R.id.order);
        arr = new ArrayAdapter<String>(this,
                  android.support.constraint.R.layout.support_simple_spinner_dropdown_item, pizzas);
        orderListView.setAdapter(arr);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                orderListView.setItemChecked(i, true);
                isSelected = true;
                selectedPizza = i;
            }
        });

    }

    public void placeOrder(){
        if (currentOrder.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please add something to order and try again", Toast.LENGTH_SHORT).show();
            return;
        }
        currentOrder.setStatus();

        this.storeOrders.newOrder(currentOrder.getOrderNumber()+1);
        this.currentOrder = storeOrders.getCurrentOrder();

        Intent openMainActivity = new Intent(CurrentOrderActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Order Placed Successfully!", Toast.LENGTH_SHORT).show();

    }

    public void clearOrder(){
        if (this.currentOrder.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please add something to order and try again", Toast.LENGTH_SHORT).show();
            return;
        }
        this.currentOrder.clearOrder();
        Intent openMainActivity = new Intent(CurrentOrderActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Order Successfully Cleared!", Toast.LENGTH_SHORT).show();
    }

    public void setUpPizzas(){
        ArrayList<String> temp = currentOrder.returnAsList();
        int size = temp.size();
        pizzas = new String[size];
        for (int i = 0; i < size; i++){
            pizzas[i] = temp.get(i);
        }
    }

    public void removePizza(){
        if (this.currentOrder.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please add something to order and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isSelected == false){
            Toast.makeText(getApplicationContext(), "Please select a pizza to remove it", Toast.LENGTH_SHORT).show();
            return;
        }

        this.currentOrder.subtractPrice(currentOrder.getPizza(selectedPizza).price());
        this.currentOrder.removePizza(selectedPizza);
        Intent openMainActivity = new Intent(CurrentOrderActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Pizza Successfully removed from order", Toast.LENGTH_SHORT).show();

    }
    
}