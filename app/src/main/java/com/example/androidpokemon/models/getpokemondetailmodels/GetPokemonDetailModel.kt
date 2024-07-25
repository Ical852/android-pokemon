package com.example.androidpokemon.models.getpokemondetailmodels

import com.example.androidpokemon.models.getpokemonspeciesmodel.GetPokemonSpeciesModel
import com.example.androidpokemon.utils.Utils
import java.io.Serializable

data class TypeModel(
    val slot: Int?,
    val type: TypeDetailModel?
): Serializable

data class TypeDetailModel(
    val name: String?,
    val url: String?
): Serializable

data class StatsModel(
    val baseStat: Int?,
    val effort: Int?,
    val stat: StatsDetailModel?
): Serializable

data class StatsDetailModel(
    val name: String?,
    val url: String?
): Serializable

data class SpeciesModel(
    val name: String?,
    val url: String?
): Serializable

data class PokemonDetailModel(
    var bgColor: String?,
    val id: Int?,
    val height: Int?,
    val order: Int?,
    val baseExperience: Int?,
    val weight: Int?,
    val isDefault: Boolean?,
    val name: String?,
    val locationAreaEncounters: String?,
    val cries: CriesModel?,
    val species: SpeciesModel?,
    val sprites: SpriteModel?,
    var group: EggGroupModel? = null,
    var speciesDetail: GetPokemonSpeciesModel? = null,
    val abilities: List<AbilityModel>?,
    val forms: List<FormsModel>?,
    val moves: List<MoveModel>?,
    val stats: List<StatsModel>?,
    val types: List<TypeModel>?,
    var evolutions: List<PokemonDetailModel>? = null
): Serializable {
    init {
        this.bgColor = Utils().getPokeColor(this.types!![0].type!!.name!!)
    }

    fun setPokemonGroup(group: EggGroupModel) {
        this.group = group
    }

    fun setPokemonEvolutions(evolutions: List<PokemonDetailModel>) {
        this.evolutions = evolutions
    }

    fun setPokemonSpeciesDetail(speciesDetail: GetPokemonSpeciesModel) {
        this.speciesDetail = speciesDetail
    }
}


data class MoveModel(
    val move: MoveDetailModel?,
    val versionGroupDetails: List<VersionGroupDetailsModel>?
): Serializable

data class MoveDetailModel(
    val name: String?,
    val url: String?
): Serializable

data class VersionGroupDetailsModel(
    val levelLearnedAt: Int?,
    val moveLearnMethod: MoveLearnMethodModel?,
    val versionGroup: VersionGroupModel?
): Serializable

data class MoveLearnMethodModel(
    val name: String?,
    val url: String?
): Serializable

data class VersionGroupModel(
    val name: String?,
    val url: String?
): Serializable

data class FormsModel(
    val name: String?,
    val url: String?
): Serializable

data class EggGroupModel(
    val id: Int?,
    val name: String?
): Serializable

data class CriesModel(
    val latest: String?,
    val legacy: String?
): Serializable

data class SpriteModel(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?,
    val other: OtherModel?
): Serializable {
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "back_default" to this.backDefault,
            "back_female" to this.backFemale,
            "back_shiny" to this.backShiny,
            "back_shiny_female" to this.backShinyFemale,
            "front_default" to this.frontDefault,
            "front_female" to this.frontFemale,
            "front_shiny" to this.frontShiny,
            "front_shiny_female" to this.frontShinyFemale,
            "other" to this.other
        )
    }
}

data class DreamWorldModel(
    val frontDefault: String?,
    val frontFemale: String?
): Serializable

data class HomeModel(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
): Serializable

data class OfficialArtworkModel(
    val frontDefault: String?,
    val frontShiny: String?
): Serializable

data class OtherModel(
    val dreamWorld: DreamWorldModel?,
    val home: HomeModel?,
    val officialArtwork: OfficialArtworkModel?,
    val showdown: ShowdownModel?
): Serializable

data class ShowdownModel(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
): Serializable {
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "back_default" to this.backDefault,
            "back_female" to this.backFemale,
            "back_shiny" to this.backShiny,
            "back_shiny_female" to this.backShinyFemale,
            "front_default" to this.frontDefault,
            "front_female" to this.frontFemale,
            "front_shiny" to this.frontShiny,
            "front_shiny_female" to this.frontShinyFemale
        )
    }
}

data class AbilityDetailModel(
    val name: String?,
    val url: String?
): Serializable

data class AbilityModel(
    val ability: AbilityDetailModel?,
    val isHidden: Boolean?,
    val slot: Int?
): Serializable
