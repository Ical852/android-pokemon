package com.example.androidpokemon.services

import com.example.androidpokemon.models.getallpokemonmodels.PokemonModel
import com.example.androidpokemon.models.getpokemoncolormodels.GetPokemonColorModel
import com.example.androidpokemon.models.getpokemondetailmodels.EggGroupModel
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel
import com.example.androidpokemon.models.getpokemonevolutionmodels.GetPokemonEvolutionModel
import com.example.androidpokemon.models.getpokemonspeciesmodel.GetPokemonSpeciesModel
import org.koin.dsl.module

val pokemonRepository = module {
    factory { PokemonRepository(get()) }
}

class PokemonRepository (
    private val api: PokemonApiClient,
) {
    suspend fun getAllPokemon() : PokemonModel? {
        return api.getAllPokemon()
    }

    suspend fun extendPokemon(url: String) : PokemonModel? {
        return api.extendPokemon(url)
    }

    suspend fun getPokemonDetail(url: String) : PokemonDetailModel? {
        return api.getPokemonDetail(url)
    }

    suspend fun getPokemonColor(id: Int) : GetPokemonColorModel? {
        return api.getPokemonColor(id)
    }

    suspend fun getPokemonGroup(id: Int) : EggGroupModel? {
        return api.getPokemonGroup(id)
    }

    suspend fun getPokemonSpecies(id: Int) : GetPokemonSpeciesModel? {
        return api.getPokemonSpecies(id)
    }

    suspend fun getPokemonEvolutions(url: String) : GetPokemonEvolutionModel? {
        return api.getPokemonEvolutions(url)
    }
}