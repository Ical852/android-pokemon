package com.example.androidpokemon.screens.main.tabs.mypokemon

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpokemon.models.pokemonmodels.PokemonModel
import com.example.androidpokemon.models.restmodels.response.GetAllMyPokemonModel
import com.example.androidpokemon.services.pokemonservices.PokemonRepository
import com.example.androidpokemon.services.restservices.RestRepository
import com.example.androidpokemon.utils.Utils
import kotlinx.coroutines.launch
import org.koin.dsl.module

val myPokemonViewModel = module {
    factory { MyPokemonViewModel( get(), get() ) }
}

class MyPokemonViewModel(
    private val repository: RestRepository,
    private val pokeRepository: PokemonRepository
): ViewModel() {
    val loading by lazy { MutableLiveData<Boolean>() }
    val failed by lazy { MutableLiveData<Boolean>() }
    val message by lazy { MutableLiveData<String>() }
    val pokemons by lazy { MutableLiveData<GetAllMyPokemonModel?>(null) }
    val count by lazy { MutableLiveData<Int>(0) }
    val maxCount by lazy { MutableLiveData<Int>(0) }

    init {
        fetch()
    }

    fun fetch() {
        count.value = 0
        maxCount.value = 0
        loading.value = true
        failed.value = false

        viewModelScope.launch {
            try {
                val myPokemon = repository.getAllMyPokemon()
                    ?: throw Exception("Something Went Wrong")
                if (myPokemon.data == null) throw Exception("Something Went Wrong")
                maxCount.value = myPokemon.data.size

                val list = myPokemon.data
                for ((index, value) in list.withIndex()) {
                    val detail = pokeRepository.getPokemonDetail(value.url)
                        ?: throw Exception("Something Went Wrong")
                    value.setPokemonDetail(detail)
                    if (detail.id == null) throw Exception("Something Went Wrong")

                    try {
                        val group = pokeRepository.getPokemonGroup(detail.id)
                        if (group != null) {
                            value.detail!!.setPokemonGroup(group)
                        }
                    } catch (_: Exception) {}

                    val species = pokeRepository.getPokemonSpecies(detail.id)
                    if (species == null) throw Exception("Something Went Wrong")
                    value.detail!!.setPokemonSpeciesDetail(species)

                    count.value = count.value?.plus(1)
                }

                pokemons.value = myPokemon

                loading.value = false
                count.value = 0
            } catch (e: Exception) {
                message.value = "Something went wrong " + e.message
                loading.value = false
                count.value = 0
                failed.value = true
            }
        }
    }
}