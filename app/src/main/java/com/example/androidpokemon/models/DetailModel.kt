package com.example.androidpokemon.models

import com.example.androidpokemon.models.getpokemondetailmodels.StatsModel
import com.example.androidpokemon.models.getpokemondetailmodels.TypeModel
import java.io.Serializable

data class DetailModel(
    val img: String,
    val name: String,
    val baseStats: List<StatsModel>,
    val types: List<TypeModel>
): Serializable