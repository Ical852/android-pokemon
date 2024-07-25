package com.example.androidpokemon.models.getpokemonevolutionmodels

import java.io.Serializable

data class SpeciesModel(
    val name: String?,
    val url: String?
): Serializable

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