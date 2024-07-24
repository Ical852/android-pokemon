package com.example.androidpokemon.models.getpokemonevolutionmodels

data class SpeciesModel(
    val name: String?,
    val url: String?
)

data class EvolveToModel(
    val isBaby: Boolean?,
    val species: SpeciesModel?,
    val evolvesTo: List<EvolveToModel>?
)

data class ChainModel(
    val isBaby: Boolean?,
    val species: SpeciesModel?,
    val evolvesTo: List<EvolveToModel>?
)

data class GetPokemonEvolutionModel(
    val id: Int?,
    val chain: ChainModel?,
    val babyTriggerItem: Any?
)