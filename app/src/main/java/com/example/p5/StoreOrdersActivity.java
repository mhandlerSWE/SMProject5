package com.example.p5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StoreOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}