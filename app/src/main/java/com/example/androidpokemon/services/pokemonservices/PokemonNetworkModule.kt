package com.example.androidpokemon.services.pokemonservices

import com.example.androidpokemon.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { providePokemonOkhttpClient() }
    single { providePokemonRetrofit( get() ) }
    single { providePokemonsApi( get() ) }
}

data class PokemonHttpClient(
    val httpClient: OkHttpClient
)
fun providePokemonOkhttpClient() : PokemonHttpClient {
    val httpClient = PokemonHttpClient(
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    )
    return httpClient
}

data class PokemonRetrofit(
    val retrofit: Retrofit
)
val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()
fun providePokemonRetrofit(pokemonHttp: PokemonHttpClient) : PokemonRetrofit {
    val retrofit = PokemonRetrofit(
        Retrofit.Builder()
            .baseUrl( BuildConfig.BASE_URL )
            .client( pokemonHttp.httpClient )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    )
    return retrofit
}

fun providePokemonsApi(pokemonRetrofit: PokemonRetrofit) : PokemonApiClient {
    return pokemonRetrofit.retrofit.create(PokemonApiClient::class.java)
}
