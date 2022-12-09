package com.example.p5;
/**
 * Adapter class for recyclerView
 * Allows Order Pizza Activity to function as desired
 ....
 @author Max Handler
 @author Luke Rivera
 ....
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private final String selectedColor = "#CA5353";
    private final String blankBackground = "#FFFFFF";
    private ArrayList<PizzaOption> typeList;
    private Context context;
    private int checkedPosition = -1;
    private OrderPizzaActivity parent;
    private Pizza currentPizza;

    /**
     * Constructor for RecyclerAdapter
     * @param typeList
     * @param parent
     */
    public recyclerAdapter(ArrayList<PizzaOption> typeList, OrderPizzaActivity parent) {
        this.typeList = typeList;
        this.parent = parent;
        currentPizza = parent.getCurrentPizza();
    }

    /**
     * Initialize view
     * @param parent
     * @param i
     * @return vieewholder containing pizza types
     */
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pizza_types, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * bind value at position i to viewHolder
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(typeList.get(i));
        //String name = typeList.get(i).getType();
        //myViewHolder.nameTxt.setText(name);
    }

    /**
     * get number of items offered
     * @return number of pizza types
     */
    @Override
    public int getItemCount() {
        return typeList.size();
    }

    /**
     *
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.uName);
        }

        void bind(final PizzaOption type) {
            int pos = getAdapterPosition();

            // no items are selected
            if(checkedPosition == -1) {
                nameTxt.setBackgroundColor(Color.parseColor(blankBackground));
            } else {
                // an item is selected items is selected
                if(checkedPosition == pos) {
                    nameTxt.setBackgroundColor(Color.parseColor(selectedColor));
                }
                else {
                    nameTxt.setBackgroundColor(Color.parseColor(blankBackground));
                }
            }
            nameTxt.setText(type.getType());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nameTxt.setBackgroundColor(Color.parseColor(selectedColor));
                    if(checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                        parent.changePic(checkedPosition);
                        PizzaOption type = typeList.get(checkedPosition);
                        implementType(type);
                        parent.setCurrentPizza(currentPizza);
                    }
                }
            }
            );
        }
    }

    /**
     * get checked position
     * @return int of checked position
     */
    public int getCheckedPosition(){
        return this.checkedPosition;
    }

    /**
     * Create new pizza based on type that user selects
     * @param type
     */
    public void implementType(PizzaOption type){
        if (type.equals(PizzaOption.CHICAGO_DELUXE)){
            PizzaFactory pizzaFactory = new ChicagoPizza();
            this.currentPizza = pizzaFactory.createDeluxe();
        }
        else if (type.equals(PizzaOption.CHICAGO_BBQ)){
            PizzaFactory pizzaFactory = new ChicagoPizza();
            this.currentPizza = pizzaFactory.createBBQChicken();
        }
        else if (type.equals(PizzaOption.CHICAGO_MEATZZA)){
            PizzaFactory pizzaFactory = new ChicagoPizza();
            this.currentPizza = pizzaFactory.createMeatzza();
        }
        else if (type.equals(PizzaOption.CHICAGO_BYO)){
            PizzaFactory pizzaFactory = new ChicagoPizza();
            this.currentPizza = pizzaFactory.createBuildYourOwn();
        }
        else if (type.equals(PizzaOption.NEW_YORK_BBQ)){
            PizzaFactory pizzaFactory = new NYPizza();
            this.currentPizza = pizzaFactory.createBBQChicken();
        }
        else if (type.equals(PizzaOption.NEW_YORK_DELUXE)){
            PizzaFactory pizzaFactory = new NYPizza();
            this.currentPizza = pizzaFactory.createDeluxe();

        }
        else if (type.equals(PizzaOption.NEW_YORK_MEATZZA)){
            PizzaFactory pizzaFactory = new NYPizza();
            this.currentPizza = pizzaFactory.createMeatzza();
        }
        else if (type.equals(PizzaOption.NEW_YORK_BYO)){
            PizzaFactory pizzaFactory = new NYPizza();
            this.currentPizza = pizzaFactory.createBuildYourOwn();
        }
    }
}
