package com.example.androidpokemon.screens.main.tabs.home

import androidx.lifecycle.ViewModel
import com.example.androidpokemon.services.pokemonservices.PokemonRepository
import org.koin.dsl.module

val detailViewModel = module {
    factory { DetailViewModel( get() ) }
}

class DetailViewModel(val repository: PokemonRepository): ViewModel() {
}