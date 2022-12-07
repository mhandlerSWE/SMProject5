package com.example.p5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectSizeAndToppingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] sizes = {"Small", "Medium", "Large"};

    private String[] toppings = {"Sausage", "Pepperoni",
            "Green Pepper", "Onion", "Mushroom",
            "BBQ Chicken", "Provolone", "Cheddar",
            "Beef", "Ham", "Kale", "Eggplant", "Egg"};

    private Spinner sizeSelect;

    private ListView toppingSelect;

    private Button placeOrderButton;

    private StoreOrders storeOrders;

    private Order currentOrder;

    private Pizza currentPizza;

    private int numToppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_and_toppings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);

        sizeSelect = findViewById(R.id.sizeSelect);
        sizeSelect.setOnItemSelectedListener(this);

        ArrayAdapter sizeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);

        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSelect.setAdapter(sizeAdapter);

        toppingSelect = findViewById(R.id.toppingsListView);
        ArrayAdapter<String> toppingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, toppings);
        toppingSelect.setAdapter(toppingAdapter);
        toppingSelect.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        this.storeOrders = MainActivity.getStoreOrders();
        this.currentOrder = this.storeOrders.getCurrentOrder();
        this.currentPizza = OrderPizzaActivity.currentPizza;// this.currentOrder.getCurrentPizza();
        this.currentPizza.setSize(Size.SMALL);

        if(currentPizza instanceof Deluxe){
            this.selectDeluxe();
        }
        else if (currentPizza instanceof BBQChicken){
            this.selectBBQ();
        }
        else if (currentPizza instanceof Meatzza){
            this.selectMeatzza();
        }
        else {
            toppingSelect.clearChoices();
            toppingSelect.requestLayout();
        }

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPizza();
            }
        });

        numToppings = 0;
        toppingSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(currentPizza instanceof  BuildYourOwn) {
                        if(toppingSelect.isItemChecked(i) == false) {
                            // toppingSelect.setItemChecked(i, true);
                            String topping = (String) adapterView.getItemAtPosition(i);
                            currentPizza.remove(topping.toString());
                            numToppings--;
                            return;
                        }
                        else if(numToppings == 7) {
                            // don't process the selection and display a toast that there is a maximum of 7 toppings allowed
                            toppingSelect.setItemChecked(i, false);
                            Toast.makeText(getApplicationContext(), "Cannot have more than 7 toppings!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            toppingSelect.setItemChecked(i, true);
                            String topping = (String) adapterView.getItemAtPosition(i);
                            currentPizza.add(topping.toString());
                            numToppings++;
                            Toast.makeText(getApplicationContext(), "Added " + topping + " to your pizza!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!stringToSize(sizes[i]).equals(null)){
            currentPizza.setSize(stringToSize(sizes[i]));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void orderPizza(){
        currentOrder.add(currentPizza.duplicate());
        currentPizza.reset();
        Intent openMainActivity = new Intent(SelectSizeAndToppingsActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Pizza added to order", Toast.LENGTH_SHORT).show();
    }

    private Size stringToSize(String s){
        if (s.equals("Small")){
            return Size.SMALL;
        }
        else if(s.equals("Medium")){
            return Size.MEDIUM;
        }
        else if (s.equals("Large")){
            return Size.LARGE;
        }
        return null;
    }

    public void selectDeluxe(){
        toppingSelect.setItemChecked(0, true);
        toppingSelect.setItemChecked(1, true);
        toppingSelect.setItemChecked(2, true);
        toppingSelect.setItemChecked(3, true);
        toppingSelect.setItemChecked(4, true);
        toppingSelect.setEnabled(false);
    }

    public void selectBBQ(){
        toppingSelect.setItemChecked(2, true);
        toppingSelect.setItemChecked(5, true);
        toppingSelect.setItemChecked(6, true);
        toppingSelect.setItemChecked(7, true);
        toppingSelect.setEnabled(false);
    }

    public void selectMeatzza(){
        toppingSelect.setItemChecked(0, true);
        toppingSelect.setItemChecked(1, true);
        toppingSelect.setItemChecked(8, true);
        toppingSelect.setItemChecked(9, true);
        toppingSelect.setEnabled(false);
    }

    public Topping stringToTopping(String t) {
        switch (t) {
            case "Sausage": return Topping.SAUSAGE;
            case "Pepperoni": return Topping.PEPPERONI;
            case "Green Pepper": return Topping.GREEN_PEPPER;
            case "Onion": return Topping.ONION;
            case "Mushroom": return Topping.MUSHROOM;
            case "BBQ Chicken": return Topping.BBQ_CHICKEN;
            case "Provolone": return Topping.PROVOLONE;
            case "Cheddar": return Topping.CHEDDAR;
            case "Beef": return Topping.BEEF;
            case "Ham": return Topping.HAM;
            case "Kale": return Topping.KALE;
            case "Eggplant": return Topping.EGGPLANT;
            case "Egg": return Topping.EGG;
            default: return null;
        }
    }


}