package com.example.p5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private ListView allOrders;
    SimpleAdapter arr;
    ArrayList<HashMap<String, String>> orders = new ArrayList<>();
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // connecting ListView from xml file to Java variable
        allOrders = (ListView) findViewById(R.id.allOrders);

        this.storeOrders = MainActivity.getStoreOrders();

        // setting up list view to show all orders
        orders = new ArrayList<>();
        setUpOrders();

        arr = new SimpleAdapter(this, orders, R.layout.multi_line_order,
                new String[] { "line1"},
                new int[] {R.id.pizzasInOrder});
        //arr = new ArrayAdapter<String>(this,
         //       android.support.constraint.R.layout.support_simple_spinner_dropdown_item, orders);
        allOrders.setAdapter(arr);
    }

    private void setUpOrders() {
        ArrayList<String> temp = this.storeOrders.returnAsList();
        for(int i=0;i<temp.size();i++){
            item = new HashMap<String,String>();
            item.put( "line1", temp.get(i));
            orders.add( item );
        }
    }
/*
    private void setUpOrders() {
        ArrayList<String> temp = this.storeOrders.returnAsList();
        int size = temp.size();
        orders = new String[size];
        for (int i = 0; i < size; i++){
            orders[i] = temp.get(i);
        }
    }

 */


}