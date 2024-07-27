package com.example.androidpokemon.screens.main.tabs.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpokemon.models.pokemonmodels.PokemonModel
import com.example.androidpokemon.services.pokemonservices.PokemonRepository
import com.example.androidpokemon.utils.Utils
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel( get() ) }
}

class HomeViewModel(private val repository: PokemonRepository): ViewModel() {
    val loading by lazy { MutableLiveData<Boolean>() }
    val extendLoading by lazy { MutableLiveData<Boolean>() }
    val failed by lazy { MutableLiveData<Boolean>() }
    val extendFailed by lazy { MutableLiveData<Boolean>() }
    val message by lazy { MutableLiveData<String>() }
    val pokemons by lazy { MutableLiveData<PokemonModel?>(null) }
    val count by lazy { MutableLiveData<Int>(0) }
    val extendCount by lazy { MutableLiveData<Int>(0) }

    init {
        fetch()
    }

    fun fetch(
        type: String = "normal",
        extendUrl: String? = null,
        currentPokemon: PokemonModel? = null
    ) {
        if (type == "extend") {
            extendLoading.value = true
        } else {
            loading.value = true
        }

        count.value = 0
        extendCount.value = 0
        failed.value = false
        extendFailed.value = false
        viewModelScope.launch {
            try {
                var results: PokemonModel? = null
                results = if (type == "extend") {
                    repository.extendPokemon(extendUrl!!)
                } else {
                    repository.getAllPokemon()
                }

                if (results == null) throw Exception("Something Went Wrong")
                if (results.results == null) throw Exception("Something Went Wrong")

                val list = results.results
                for ((index, value) in list!!.withIndex()) {
                    if (value.url == null) throw Exception("Something Went Wrong")

                    val detail = repository.getPokemonDetail(value.url)
                    if (detail == null) throw Exception("Something Went Wrong")
                    value.setPokemonDetail(detail)
                    if (detail.id == null) throw Exception("Something Went Wrong")

                    try {
                        val color = repository.getPokemonColor(detail.id)
                        if (color != null) {
                            value.setPokemonColor(color)
                        }
                    } catch (_: Exception) {}

                    try {
                        val group = repository.getPokemonGroup(detail.id)
                        if (group != null) {
                            value.detail!!.setPokemonGroup(group)
                        }
                    } catch (_: Exception) {}

                    val species = repository.getPokemonSpecies(detail.id)
                    if (species == null) throw Exception("Something Went Wrong")
                    value.detail!!.setPokemonSpeciesDetail(species)

                    if (type == "extend") {
                        extendCount.value = extendCount.value?.plus(1)
                    } else {
                        count.value = count.value?.plus(1)
                    }
                }

                for ((index, value) in list!!.withIndex()) {
                    val evolveUrl = value.detail!!.speciesDetail!!.evolutionChain!!.url
                    val evolves = repository.getPokemonEvolutions(evolveUrl!!)
                    if (evolves == null) throw Exception("Something Went Wrong")
                    try {
                        var setupResult = results.results
                        if (type == "extend") {
                            setupResult = results.results!! + currentPokemon!!.results!!
                        }
                        val evolutions = Utils().setupEvolutions(evolves, setupResult!!)
                        value.detail!!.setPokemonEvolutions(evolutions)
                    } catch (_: Exception){}
                }

                if (type == "extend") {
                    pokemons.value!!.updateValues(results)
                    pokemons.value = pokemons.value
                } else {
                    pokemons.value = results
                }

                loading.value = false
                extendLoading.value = false
                count.value = 0
                extendCount.value = 0
            } catch (e: Exception) {
                message.value = "Something went wrong " + e.message
                loading.value = false
                extendLoading.value = false
                count.value = 0
                extendCount.value = 0
                if (type == "extend") {
                    extendFailed.value = true
                } else {
                    failed.value = true
                }
            }
        }
    }
}