package com.example.androidpokemon.screens.detail.pokemon

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpokemon.models.restmodels.request.CatchRequestModel
import com.example.androidpokemon.models.restmodels.request.FindRequestModel
import com.example.androidpokemon.services.restservices.RestRepository
import com.example.androidpokemon.utils.Utils
import kotlinx.coroutines.launch
import org.koin.dsl.module

val detailViewModel = module {
    factory { DetailViewModel( get() ) }
}

class DetailViewModel(
    private val repository: RestRepository
): ViewModel() {
    val loading by lazy { MutableLiveData<Boolean>(false) }
    val disabled by lazy { MutableLiveData<Boolean>(true) }
    val failed by lazy { MutableLiveData<Boolean>(false) }
    val success by lazy { MutableLiveData<Boolean>(false) }

    fun check(url: String) {
        viewModelScope.launch {
            try {
                loading.value = true
                val reqUrl = FindRequestModel(url)

                val response = repository.findMyPokemon(reqUrl)
                if (response != null) {
                    if (response.status == 200) {
                        disabled.value = true
                    } else {
                        disabled.value = false
                    }
                }
                loading.value = false
            } catch (e: Exception) {
                loading.value = false
            }
        }
    }

    fun catch(nickname: String, url: String) {
        viewModelScope.launch {
            try {
                loading.value = true
                success.value = false
                failed.value = false

                val request = CatchRequestModel(nickname, url)
                val response = repository.catchPokemon(request)
                if (response != null) {
                    if (response.status == 200) {
                        success.value = true
                    } else {
                        failed.value = true
                    }
                }

                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                success.value = false
                failed.value = true
            }
        }
    }
}