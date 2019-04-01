package com.example.chon.pokedexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = "POKEMONAPI_DEMO";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            //getData();

        ApiService service = retrofit.create(ApiService.class);
        final Call<Pokemon> apiResponse = service.fetchPokemon();
        apiResponse.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                //Log.d(TAG,"On response: "+response.errorBody());
                final PokemonResult pokemonResult = response.body();
                assert pokemonResult != null;
                ArrayList<Pokemon> listaPokemon = pokemonResult.getResults();
                // Log.d(TAG,"On response: cargando");
                progressBar.setVisibility(View.GONE);
                final PokemonAdapter adapter = new PokemonAdapter(listaPokemon);
                final RecyclerView recyclerView = findViewById(R.id.recycler_view);
                final LinearLayoutManager linearLayout = new LinearLayoutManager(getBaseContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayout);
                Log.d(TAG,"On response: FINAL");
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d(TAG,"Error ON failure "+ apiResponse.toString());
            }
        });

    }
}

/*
    private void getData(){
        ApiService service = retrofit.create(ApiService.class);
        final Call<Pokemon> PokemonFetchCall = service.fetchPokemon();

        PokemonFetchCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                PokemonResult pokemonResult = response.body();
                ArrayList<Pokemon> listaPokemon = pokemonResult.getResults();

                for(int i=0;i<listaPokemon.size();i++){
                    Pokemon p = listaPokemon.get(i);
                    Log.d(TAG,"pokemon: "+p.getName());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d(TAG,"Error ON failure "+ t.getMessage());
            }
        }) ;

        /*{

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                PokemonResult pokemonResult = (PokemonResult) response.body();
                ArrayList<Pokemon> listaPokemon = pokemonResult.getResults();

                for(int i=0;i<listaPokemon.size();i++){
                    Pokemon p = listaPokemon.get(i);
                    Log.d(TAG,"pokemon: "+p.getName());
                }

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.d(TAG,"Error ON failure "+ t.getMessage());
            }
        });
    }
}
*/
