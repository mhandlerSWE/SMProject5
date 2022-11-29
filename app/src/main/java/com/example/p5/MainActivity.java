package com.example.p5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button toNY, toChicago, toCurrent, toAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toNY = (Button) findViewById(R.id.btn_ny);
        toChicago = (Button) findViewById(R.id.btn_chicago);
        toCurrent = (Button) findViewById(R.id.btn_current);
        toAll = (Button) findViewById(R.id.btn_all);

        toNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNYView();
            }
        });
        toChicago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChicagoView();
            }
        });
        toCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentView();
            }
        });
        toAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStoreOrdersView();
            }
        });
    }

    /**
     * Opens the NY Order View
     */
    public void openNYView() {
        Intent intent = new Intent(this, OrderNYActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the Chicago Order View
     */
    public void openChicagoView() {
        Intent intent = new Intent(this, OrderChicagoActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the Current Order View
     */
    public void openCurrentView() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Opens All Store Orders View
     */
    public void openStoreOrdersView() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}