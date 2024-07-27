package com.example.androidpokemon.models.restmodels.response

import com.example.androidpokemon.models.pokemonmodels.PokemonDetailModel
import java.io.Serializable

data class GetAllMyPokemonModel(
    val status: Int,
    val message: String,
    val data: List<MyPokemonModel>?
) : Serializable

data class MyPokemonModel(
    val id: Int,
    val url: String,
    val nickname: String,
    val fibonacciIndex: String,
    var detail: PokemonDetailModel? = null
) : Serializable {
    fun setPokemonDetail(detail: PokemonDetailModel?) {
        this.detail = detail
    }
}
