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
import java.util.HashMap;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private ListView allOrders;
    private Button cancelOrderButton, exportOrdersButton;
    SimpleAdapter arr;
    ArrayList<HashMap<String, String>> orders = new ArrayList<>();
    HashMap<String, String> item;

    private boolean isSelected;

    private int selectedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isSelected = false;

        // connecting ListView from xml file to Java variable
        allOrders = findViewById(R.id.allOrders);
        cancelOrderButton = (Button) findViewById(R.id.cancelOrder);

        this.storeOrders = MainActivity.getStoreOrders();

        // setting up list view to show all orders
        this.setUpOrders();

        allOrders = findViewById(R.id.allOrders);
        arr = new SimpleAdapter(this, orders, R.layout.multi_line_order,
                new String[] { "line1"},
                new int[] {R.id.pizzasInOrder});
        allOrders.setAdapter(arr);

        allOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                allOrders.setItemChecked(i, true);
                isSelected = true;
                selectedOrder = i;
            }
        });

        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeOrder();
            }
        });
    }

    private void removeOrder() {
        if (isSelected == false){
            Toast.makeText(getApplicationContext(), "Please select an to remove it", Toast.LENGTH_SHORT).show();
            return;
        }

        this.storeOrders.remove(storeOrders.getOrder(selectedOrder));
        setUpOrders();

        Intent openMainActivity = new Intent(StoreOrdersActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Order Successfully removed from order", Toast.LENGTH_SHORT).show();
    }

    private void setUpOrders() {
        ArrayList<String> temp = this.storeOrders.returnAsList();
        orders = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            item = new HashMap<String,String>();
            item.put( "line1", temp.get(i));
            orders.add( item );
        }
    }

}