package com.example.androidpokemon.services

import com.example.androidpokemon.models.getallpokemonmodels.PokemonModel
import com.example.androidpokemon.models.getpokemoncolormodels.GetPokemonColorModel
import com.example.androidpokemon.models.getpokemondetailmodels.EggGroupModel
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel
import com.example.androidpokemon.models.getpokemonevolutionmodels.GetPokemonEvolutionModel
import com.example.androidpokemon.models.getpokemonspeciesmodel.GetPokemonSpeciesModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonApiClient {
    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonModel

    @GET()
    suspend fun extendPokemon(@Url url: String): PokemonModel

    @GET
    suspend fun getPokemonDetail(@Url url: String): PokemonDetailModel?

    @GET("pokemon-color/{id}")
    suspend fun getPokemonColor(
        @Path("id") id: Int
    ) : GetPokemonColorModel?

    @GET("egg-group/{id}")
    suspend fun getPokemonGroup(
        @Path("id") id: Int
    ) : EggGroupModel?

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ) : GetPokemonSpeciesModel?

    @GET
    suspend fun getPokemonEvolutions(@Url url: String): GetPokemonEvolutionModel?
}