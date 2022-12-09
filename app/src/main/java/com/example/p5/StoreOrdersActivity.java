package com.example.p5;
/**
 * Controller class for Store Orders Activity
 * Initializes and controlas activity so user can observe and cancel order
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
import android.widget.Toast;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private ListView allOrders;
    private Button cancelOrderButton, exportOrdersButton;
    SimpleAdapter arr;
    ArrayList<HashMap<String, String>> orders = new ArrayList<>();
    HashMap<String, String> item;

    private boolean isSelected;

    private int selectedOrder;

    /**
     * Create activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isSelected = false;

        allOrders = findViewById(R.id.allOrders);
        cancelOrderButton = (Button) findViewById(R.id.cancelOrder);
        //exportOrdersButton = (Button) findViewById(R.id.exportOrder);

        this.storeOrders = MainActivity.getStoreOrders();

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

    private void exportOrder() {
        if (this.storeOrders.getNumOrders() == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty Order");
            alert.setMessage("Nothing in current order. Please add something and try again.");
            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.create().show();
        }
        DecimalFormat df = new DecimalFormat("###,##0.00");

        try {
            PrintWriter out = new PrintWriter("orders.txt");

            for(int i = 0; i < this.storeOrders.getList().size(); ++i) {
                Order o = (Order)this.storeOrders.getList().get(i);
                out.println("\n\nOrder #" + (i + 1) + ":");
                Iterator var5 = o.getList().iterator();

                while(var5.hasNext()) {
                    Pizza pizza = (Pizza)var5.next();
                    out.println("\t" + pizza.toString() + "......." + df.format((o.getPrice())));
                }

                out.println("\nSubtotal:");
                String var10001 = df.format(o.getPrice());
                out.println("\t" + var10001);
                out.println("\nTax:");
                var10001 = df.format(o.getTax());
                out.println("\t" + var10001);
                out.println("\nTotal:");
                var10001 = df.format(o.getTotal());
                out.println("\t" + var10001);
            }

            out.close();
        }
        catch (Exception var7) {
        }
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
        for(int i=0;i<temp.size() - 1;i++){
            item = new HashMap<String,String>();
            item.put( "line1", temp.get(i));
            orders.add( item );
        }
    }

}