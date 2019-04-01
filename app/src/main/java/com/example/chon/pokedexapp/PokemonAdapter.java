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
import android.widget.TextView;

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
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_pokemon, viewGroup, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        final Pokemon pokemon = dataset.get(position);

        viewHolder.name.setText(pokemon.getName());
        viewHolder.Id.setText("N.º "+pokemon.getNumber() );


        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.picture);


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int temp = pokemon.getNumber();

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name",pokemon.getName());
                intent.putExtra("IDpokemon",pokemon.getNumber());

                context.startActivities(new Intent[]{intent});

            }
        });

        Log.d(TAG,"on blind viewholder");
    }


    @Override
    public int getItemCount() {
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
        @BindView(R.id.Id)
        TextView Id;
        @BindView(R.id.parent_layout)
        LinearLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
