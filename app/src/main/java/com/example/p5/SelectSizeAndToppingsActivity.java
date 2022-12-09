package com.example.p5;
/**
 * Controller class for Select Sizes and Toppings Activity
 * Initializes and controls activity so user can sleect types/size they want
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
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

    /**
     * create activity
     * @param savedInstanceState
     */
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

        this.checkPizza();

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPizza();
            }
        });

        numToppings = 0;
        this.setListView();
    }

    /**
     * Check type of pizza user selected and set up listview accordingly
     */
    public void checkPizza(){
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
    }

    /**
     * make list view for toppings functional
     */
    public void setListView(){
        toppingSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(currentPizza instanceof  BuildYourOwn) {
                    if(toppingSelect.isItemChecked(i) == false) {
                        // toppingSelect.setItemChecked(i, true);
                        String topping = (String) adapterView.getItemAtPosition(i);
                        currentPizza.remove(stringToTopping(topping));
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
                        currentPizza.add(stringToTopping(topping));
                        numToppings++;
                        Toast.makeText(getApplicationContext(), "Added " + topping + " to your pizza!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Set size of current pizza based on size user selects
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!stringToSize(sizes[i]).equals(null)){
            currentPizza.setSize(stringToSize(sizes[i]));
        }
    }

    /**
     * do nothing when nothing is selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Add current pizza to order
     */
    private void orderPizza(){
        currentOrder.add(currentPizza.duplicate());
        currentOrder.changePrice(currentPizza.price());
        currentPizza.reset();
        Intent openMainActivity = new Intent(SelectSizeAndToppingsActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
        Toast.makeText(getApplicationContext(), "Pizza added to order", Toast.LENGTH_SHORT).show();
    }

    /**
     * Get size based on string input from spinner
     * @param s
     * @return Size corresponding to input
     */
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

    /**
     * Automatically select toppings for Deluxe Pizza
     */
    public void selectDeluxe(){
        toppingSelect.setItemChecked(0, true);
        toppingSelect.setItemChecked(1, true);
        toppingSelect.setItemChecked(2, true);
        toppingSelect.setItemChecked(3, true);
        toppingSelect.setItemChecked(4, true);
        toppingSelect.setEnabled(false);
    }

    /**
     * Automatically select toppings for BBQ Chicken Pizza
     */
    public void selectBBQ(){
        toppingSelect.setItemChecked(2, true);
        toppingSelect.setItemChecked(5, true);
        toppingSelect.setItemChecked(6, true);
        toppingSelect.setItemChecked(7, true);
        toppingSelect.setEnabled(false);
    }

    /**
     * Automatically select toppings for Meatzza
     */
    public void selectMeatzza(){
        toppingSelect.setItemChecked(0, true);
        toppingSelect.setItemChecked(1, true);
        toppingSelect.setItemChecked(8, true);
        toppingSelect.setItemChecked(9, true);
        toppingSelect.setEnabled(false);
    }

    /**
     * Get topping based on string input from listView
     * @param t
     * @return Topping corresponding to input
     */
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