package com.example.androidpokemon.models.pokemonmodels

import java.io.Serializable

data class EvolveToModel(
    val isBaby: Boolean?,
    val species: SpeciesModel?,
    val evolvesTo: List<EvolveToModel>?
): Serializable

data class ChainModel(
    val isBaby: Boolean?,
    val species: SpeciesModel?,
    val evolvesTo: List<EvolveToModel>?
): Serializable

data class GetPokemonEvolutionModel(
    val id: Int?,
    val chain: ChainModel?,
    val babyTriggerItem: Any?
): Serializable