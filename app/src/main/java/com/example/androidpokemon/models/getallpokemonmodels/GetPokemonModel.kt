package com.example.androidpokemon.models.getallpokemonmodels

import com.example.androidpokemon.models.getpokemoncolormodels.GetPokemonColorModel
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel

data class PokemonResultModel(
    val name: String?,
    val url: String?,
    var detail: PokemonDetailModel? = null,
    var color: GetPokemonColorModel? = null
) {
    fun setPokemonDetail(detail: PokemonDetailModel) {
        this.detail = detail
    }

    fun setPokemonColor(color: GetPokemonColorModel) {
        this.color = color
    }
}

data class PokemonModel(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: List<PokemonResultModel>?
) {
    fun updateValues(pokemon: PokemonModel) {
        if (this.count != null && pokemon.count != null) {
            this.count = this.count!! + pokemon.count!!
        }

        this.next = pokemon.next
        this.previous = pokemon.previous

        if (this.results != null && pokemon.results != null) {
            if (this.results!!.isNotEmpty() && pokemon.results!!.isNotEmpty()) {
                this.results = this.results!! + pokemon.results!!
            }
        }
    }
}
