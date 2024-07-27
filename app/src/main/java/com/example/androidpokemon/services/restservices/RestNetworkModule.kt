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
    single { provideOkhttpClient() }
    single { provideRetrofit( get() ) }
    single { provideNewsApi( get() ) }
}

fun provideOkhttpClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
}

val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl( BuildConfig.REST_BASE_URL )
        .client( okHttpClient )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideNewsApi(retrofit: Retrofit) : RestApiClient = retrofit.create(RestApiClient::class.java)