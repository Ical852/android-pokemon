package com.example.androidpokemon.models.pokemonmodels

import java.io.Serializable

data class DetailModel(
    val img: String,
    val name: String,
    val baseStats: List<StatsModel>,
    val types: List<TypeModel>
): Serializable