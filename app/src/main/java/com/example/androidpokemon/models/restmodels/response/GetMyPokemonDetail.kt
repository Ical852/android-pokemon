package com.example.androidpokemon.models.restmodels.response

import java.io.Serializable

data class GetMyPokemonDetail(
    val status: Int,
    val message: String,
    val data: MyPokemonModel
) : Serializable
