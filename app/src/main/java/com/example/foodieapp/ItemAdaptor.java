package com.example.foodieapp;


import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.MealItemHolder> {
    MainActivity mainActivity;
    ArrayList<Items> ItemArrayList;

    public ItemAdaptor(MainActivity mainActivity, ArrayList<Items> mealItemArrayList) {
        this.mainActivity = mainActivity;
        this.ItemArrayList = mealItemArrayList;
        load();
    }

    @NonNull
    @Override
    public MealItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent,false);
        return new MealItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemHolder holder, int position) {
        holder.title.setText(ItemArrayList.get(position).getTitle());
        holder.description.setText(ItemArrayList.get(position).getTopic());
        String imguri = ItemArrayList.get(position).getImage();
        Picasso.get().load(imguri).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return ItemArrayList.size();
    }

    public class MealItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView title, description;
        public ImageView image;
        public MealItemHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.img);
        }

        @Override
        public void onClick(View view) {



            Intent intent = new Intent(mainActivity.getApplicationContext(), DetailsActivity.class);
            intent.putExtra("TITLE", ItemArrayList.get(getAdapterPosition()).getTitle());
            intent.putExtra("TOPIC", ItemArrayList.get(getAdapterPosition()).getTopic());
            intent.putExtra("HV1", ItemArrayList.get(getAdapterPosition()).getHv()[0]);
            intent.putExtra("HV2", ItemArrayList.get(getAdapterPosition()).getHv()[1]);
            intent.putExtra("CV1", ItemArrayList.get(getAdapterPosition()).getCv()[0]);
            intent.putExtra("CV2", ItemArrayList.get(getAdapterPosition()).getCv()[1]);
            intent.putExtra("C1", ItemArrayList.get(getAdapterPosition()).getC()[0]);
            intent.putExtra("C2", ItemArrayList.get(getAdapterPosition()).getC()[1]);
            intent.putExtra("CL1", ItemArrayList.get(getAdapterPosition()).getCl()[0]);
            intent.putExtra("CL2", ItemArrayList.get(getAdapterPosition()).getCl()[1]);
            intent.putExtra("S1", ItemArrayList.get(getAdapterPosition()).getS()[0]);
            intent.putExtra("S2", ItemArrayList.get(getAdapterPosition()).getS()[1]);
            intent.putExtra("IMAGE", ItemArrayList.get(getAdapterPosition()).getImage());
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mainActivity.getApplicationContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            SharedPreferences user = mainActivity.getSharedPreferences("LOGIN",MODE_PRIVATE);
            String use = user.getString("USER",null);
            if(use.equals("admin")){   // admin only can delete chapters
                ItemArrayList.remove(getAdapterPosition());
                save();
                notifyDataSetChanged();
            }

            return true;
        }
    }

    private void load() {
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("SHARED PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MEALS_OBJ", null);
        Type type = new TypeToken<ArrayList<Items>>() {}.getType();
        ItemArrayList = gson.fromJson(json, type);
        if (ItemArrayList == null) {
            ItemArrayList = new ArrayList<>();
        }
    }

    private void save() {
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("SHARED PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ItemArrayList);
        editor.putString("MEALS_OBJ", json);
        editor.apply();
    }
}
