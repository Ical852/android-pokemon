package com.example.androidpokemon.models.getpokemoncolormodels

data class GetPokemonColorModel(
    val id: Int?,
    val name: String?,
    val names: List<NameModel>?,
    val pokemonSpecies: List<SpeciesModel>?
)

data class SpeciesModel(
    val name: String?,
    val url: String?
)

data class NameModel(
    val name: String?,
    val language: LanguageModel?
)

data class LanguageModel(
    val name: String?,
    val url: String?
)
