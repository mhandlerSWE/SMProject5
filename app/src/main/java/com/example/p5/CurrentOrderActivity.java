package com.example.p5;
/**
 * Class to control Current Order Activity
 * Allows user to observe, change, cancel, or place their order
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrentOrderActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private Order currentOrder;
    SimpleAdapter arr;
    ArrayList<HashMap<String, String>> pizzas = new ArrayList<>();
    HashMap<String, String> item;

    private ListView orderListView;

    private Button placeOrder, clearOrder, removeSelected;

    private TextView price;

    private boolean isSelected;

    private int selectedPizza;


    /**
     * Create activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isSelected = false;

        this.setButtons();

        this.storeOrders = MainActivity.getStoreOrders();
        this.currentOrder = this.storeOrders.getCurrentOrder();

        price = (TextView) findViewById(R.id.price);
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String priceS = "Order Price: $" + df.format(this.currentOrder.getPrice()) + "     Sales Tax: $" + df.format(this.currentOrder.getTax()) + "     Order Total: $"+df.format(this.currentOrder.getTotal());
        price.setText(priceS);

        this.setUpPizzas();

        orderListView = findViewById(R.id.order);
        arr = new SimpleAdapter(this, pizzas, R.layout.multi_line_order,
                new String[] { "line1"},
                new int[] {R.id.pizzasInOrder});
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

    /**
     * Initialize buttons so they do something when clicked
     */
    public void setButtons(){
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
    }

    /**
     * Place current order, call from button click
     */
    public void placeOrder(){
        if (currentOrder.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty Order");
            alert.setMessage("Nothing in current order. Please add something and try again.");
            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.create().show();
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

    /**
     * Clear current order, call from button click
     */
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


    /**
     * Initialize list of pizzas in order
     */
    public void setUpPizzas(){
        ArrayList<String> temp = this.currentOrder.returnAsList();
        for(int i=0;i<temp.size();i++){
            item = new HashMap<String,String>();
            item.put( "line1", temp.get(i));
            pizzas.add( item );
        }
    }

    /**
     * remove selected pizza from order, call from button click
     */
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

    /*

        this.subtotalTextField.setText("$"+df.format(this.currentOrder.getPrice()));
        this.salesTaxTextField.setText("$"+df.format(this.currentOrder.getTax()));
        this.orderTotalTextField.setText("$"+df.format(this.currentOrder.getTotal()));


     */
}