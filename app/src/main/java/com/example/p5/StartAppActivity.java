package com.example.p5;
/**
 * Controller class to start up the app
 * Initializes central data for the rest of the app experience
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartAppActivity extends AppCompatActivity {

    public static StoreOrders storeOrders;

    /**
     * Create view & store orders
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        storeOrders = new StoreOrders();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}