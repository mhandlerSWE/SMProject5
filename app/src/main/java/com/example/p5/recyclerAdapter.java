package com.example.p5;

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

    public recyclerAdapter(ArrayList<PizzaOption> typeList, OrderPizzaActivity parent) {
        this.typeList = typeList;
        this.parent = parent;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pizza_types, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(typeList.get(i));
        //String name = typeList.get(i).getType();
        //myViewHolder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

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
                    }
                }
            }
            );
        }
    }

    public int getCheckedPosition(){
        return this.checkedPosition;
    }
}
