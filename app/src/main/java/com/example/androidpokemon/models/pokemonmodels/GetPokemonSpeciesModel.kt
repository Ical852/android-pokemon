package com.example.androidpokemon.models.pokemonmodels

import java.io.Serializable

data class GetPokemonSpeciesModel(
    val evolutionChain: EvolutionChainModel?
): Serializable

data class EvolutionChainModel(
    val url: String?
): Serializable