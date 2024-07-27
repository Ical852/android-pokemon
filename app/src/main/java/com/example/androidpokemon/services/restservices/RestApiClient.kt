package com.example.androidpokemon.services.restservices

import com.example.androidpokemon.models.restmodels.request.CatchRequestModel
import com.example.androidpokemon.models.restmodels.request.RenameRequestModel
import com.example.androidpokemon.models.restmodels.response.GetAllMyPokemonModel
import com.example.androidpokemon.models.restmodels.response.GetMyPokemonDetail
import com.example.androidpokemon.models.restmodels.response.PostResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestApiClient {
    @GET("pokemons")
    suspend fun getAllMyPokemon(): GetAllMyPokemonModel

    @GET("find/{url}")
    suspend fun findMyPokemon(
        @Path("url") url: String
    ): GetMyPokemonDetail

    @POST("catch")
    suspend fun catchPokemon(
        @Body request: CatchRequestModel
    ): PostResponseModel

    @POST("release/{id}")
    suspend fun releasePokemon(
        @Path("id") id: Int
    ): PostResponseModel

    @POST("rename/{id}")
    suspend fun renamePokemon(
        @Path("id") id: Int,
        @Body request: RenameRequestModel
    ): PostResponseModel
}