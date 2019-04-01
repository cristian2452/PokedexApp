package com.example.chon.pokedexapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        int iDpokemon = getIntent().getIntExtra("IDpokemon",0);
        String name = getIntent().getStringExtra("name");

        setInfo(name,iDpokemon);
    }

    private void setInfo(String name, int ID){

        TextView widgetName = findViewById(R.id.detail_name);
        widgetName.setText("Name: "+name);

        TextView widgetType = findViewById(R.id.detail_id);
        widgetType.setText("N.º "+ID);


        ImageView widgetImage = findViewById(R.id.detail_pokemonPicture);
        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + ID + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(widgetImage);
    }
}