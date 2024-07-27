package com.example.androidpokemon.models.pokemonmodels

import java.io.Serializable

data class PokemonResultModel(
    val name: String?,
    val url: String?,
    var detail: PokemonDetailModel? = null,
    var color: GetPokemonColorModel? = null
): Serializable {
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
): Serializable {
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
