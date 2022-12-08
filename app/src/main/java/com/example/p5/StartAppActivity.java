package com.example.p5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartAppActivity extends AppCompatActivity {

    public static StoreOrders storeOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        storeOrders = new StoreOrders();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}