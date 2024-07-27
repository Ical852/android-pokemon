package com.example.androidpokemon.screens.detail.mypokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpokemon.models.restmodels.request.RenameRequestModel
import com.example.androidpokemon.services.restservices.RestRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val pokemonDetailViewModel = module {
    factory { MyPokemonDetailViewModel( get() ) }
}

class MyPokemonDetailViewModel(val repository: RestRepository): ViewModel() {
    val loading by lazy { MutableLiveData<Boolean>(false) }
    val failedRelease by lazy { MutableLiveData<Boolean>(false) }
    val successRelease by lazy { MutableLiveData<Boolean>(false) }
    val failedRename by lazy { MutableLiveData<Boolean>(false) }
    val successRename by lazy { MutableLiveData<Boolean>(false) }

    fun rename(nickname: String, id: Int) {
        viewModelScope.launch {
            try {
                loading.value = true
                failedRename.value = false
                successRename.value = false

                val request = RenameRequestModel(nickname)
                val response = repository.renamePokemon(
                    id, request
                )
                if (response != null) {
                    if (response.status == 200) {
                        successRename.value = true
                    } else {
                        failedRename.value = true
                    }
                }

                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                failedRename.value = true
            }
        }
    }

    fun release(id: Int) {
        viewModelScope.launch {
            try {
                loading.value = true
                failedRelease.value = false
                successRename.value = false

                val response = repository.releasePokemon(id)
                if (response != null) {
                    if (response.status == 200) {
                        successRename.value = true
                    } else {
                        failedRelease.value = true
                    }
                }

                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                failedRelease.value = true
            }
        }
    }
}