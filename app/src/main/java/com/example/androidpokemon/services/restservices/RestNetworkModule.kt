package com.example.androidpokemon.services.restservices

import com.example.androidpokemon.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val restNetworkModule = module {
    single { provideRestOkhttpClient() }
    single { provideRestRetrofit( get() ) }
    single { provideRestApi( get() ) }
}

data class RestHttpClient(
    val httpClient: OkHttpClient
)
fun provideRestOkhttpClient() : RestHttpClient {
    val httpClient = RestHttpClient(
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    )
    return httpClient
}

data class RestRetrofit(
    val retrofit: Retrofit
)
val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()
fun provideRestRetrofit(restHttpClient: RestHttpClient) : RestRetrofit {
    val retrofit = RestRetrofit(
        Retrofit.Builder()
            .baseUrl( BuildConfig.REST_BASE_URL )
            .client( restHttpClient.httpClient )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    )
    return retrofit
}

fun provideRestApi(restRetrofit: RestRetrofit) : RestApiClient {
    return restRetrofit.retrofit.create(RestApiClient::class.java)
}