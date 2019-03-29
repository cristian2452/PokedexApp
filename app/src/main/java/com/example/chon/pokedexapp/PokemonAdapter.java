package com.example.chon.pokedexapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private List<Pokemon> dataset;
    private Context context;
    private String TAG = "POKEMONAPI_DEMO";

    public PokemonAdapter(List<Pokemon> datasets) {
        dataset = datasets;
        Log.d(TAG,"set dataset");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_pokemon, viewGroup, false);
        Log.d(TAG,"on create viewholder");
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        final Pokemon pokemon = dataset.get(position);

        viewHolder.name.setText(pokemon.getName());

        /*
        Glide.with(context)
                .load(beer.getImageUrl())
                .into(viewHolder.picture);
        */

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.picture);

        /*
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,beer.getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name",beer.getName());
                intent.putExtra("imageUrl",beer.getImageUrl());
                intent.putExtra("type",beer.getType());
                intent.putExtra("category",beer.getCategory());
                intent.putExtra("country",beer.getCountry());
                intent.putExtra("abv",beer.getAbv());
                intent.putExtra("brewer",beer.getBrewer());

                context.startActivities(new Intent[]{intent});

            }
        });
        */
        Log.d(TAG,"on blind viewholder");
    }


    @Override
    public int getItemCount() {
        Log.d(TAG,"on get item"+dataset.size());
        return dataset.size();
    }

    public void addPokemonList(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.pokemonPicture)
        ImageView picture;
        //@BindView(R.id.parent_layout)
        //LinearLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
