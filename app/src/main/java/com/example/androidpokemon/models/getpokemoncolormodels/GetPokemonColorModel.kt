package com.example.androidpokemon.models.getpokemoncolormodels

import java.io.Serializable

data class GetPokemonColorModel(
    val id: Int?,
    val name: String?,
    val names: List<NameModel>?,
    val pokemonSpecies: List<SpeciesModel>?
): Serializable

data class SpeciesModel(
    val name: String?,
    val url: String?
): Serializable

data class NameModel(
    val name: String?,
    val language: LanguageModel?
): Serializable

data class LanguageModel(
    val name: String?,
    val url: String?
): Serializable
