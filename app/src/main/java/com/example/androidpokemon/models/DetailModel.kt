package com.example.androidpokemon.models

import com.example.androidpokemon.models.pokemonmodels.StatsModel
import com.example.androidpokemon.models.pokemonmodels.TypeModel
import java.io.Serializable

data class DetailModel(
    val img: String,
    val name: String,
    val baseStats: List<StatsModel>,
    val types: List<TypeModel>,
    val url: String? = "",
    val id: Int = 0,
): Serializable