package com.example.androidpokemon.models.getpokemonspeciesmodel

data class GetPokemonSpeciesModel(
    val evolutionChain: EvolutionChainModel?
)

data class EvolutionChainModel(
    val url: String?
)