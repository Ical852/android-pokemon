package com.example.androidpokemon.models.restmodels.response

import java.io.Serializable

data class GetAllMyPokemonModel(
    val status: Int,
    val message: String,
    val data: List<MyPokemonModel>
) : Serializable

data class MyPokemonModel(
    val id: Int,
    val url: String,
    val nickname: String,
    val fibonacciIndex: String
) : Serializable
