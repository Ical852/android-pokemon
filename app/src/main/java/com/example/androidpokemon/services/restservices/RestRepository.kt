package com.example.androidpokemon.services.restservices

import com.example.androidpokemon.models.restmodels.request.CatchRequestModel
import com.example.androidpokemon.models.restmodels.request.FindRequestModel
import com.example.androidpokemon.models.restmodels.request.RenameRequestModel
import com.example.androidpokemon.models.restmodels.response.GetAllMyPokemonModel
import com.example.androidpokemon.models.restmodels.response.GetMyPokemonDetail
import com.example.androidpokemon.models.restmodels.response.PostResponseModel
import org.koin.dsl.module

val restRepository = module {
    factory { RestRepository(get()) }
}

class RestRepository (
    private val api: RestApiClient,
) {
    suspend fun getAllMyPokemon() : GetAllMyPokemonModel? {
        return api.getAllMyPokemon()
    }

    suspend fun findMyPokemon(
        requestModel: FindRequestModel
    ) : GetMyPokemonDetail? {
        return api.findMyPokemon(requestModel)
    }

    suspend fun catchPokemon(
        request: CatchRequestModel
    ): PostResponseModel? {
        return api.catchPokemon(request)
    }

    suspend fun releasePokemon(
        id: Int
    ): PostResponseModel? {
        return api.releasePokemon(id)
    }

    suspend fun renamePokemon(
        id: Int,
        request: RenameRequestModel
    ): PostResponseModel? {
        return api.renamePokemon(id, request)
    }
}