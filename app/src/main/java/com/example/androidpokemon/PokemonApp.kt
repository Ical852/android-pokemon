package com.example.androidpokemon

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidpokemon.screens.detail.detailModule
import com.example.androidpokemon.screens.main.tabs.home.detailViewModel
import com.example.androidpokemon.screens.main.tabs.home.homeModule
import com.example.androidpokemon.screens.main.tabs.home.homeViewModel
import com.example.androidpokemon.services.pokemonservices.networkModule
import com.example.androidpokemon.services.pokemonservices.pokemonRepository
import com.example.androidpokemon.services.restservices.restNetworkModule
import com.example.androidpokemon.services.restservices.restRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class PokemonApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree() )
        Timber.e("Run Pokemon App")
        AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO )
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PokemonApp)
            modules(
                listOf(
                    detailModule,
                    detailViewModel,
                    homeModule,
                    homeViewModel,
                    restNetworkModule,
                    restRepository,
                    networkModule,
                    pokemonRepository,
                )
            )
        }
    }
}