package com.example.chon.pokedexapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("pokemon")
    Call<List<Pokemon>> fetchPokemon();
}