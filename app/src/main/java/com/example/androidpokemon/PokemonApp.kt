package com.example.androidpokemon

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class PokemonApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant( Timber.DebugTree() )
        Timber.e("Run Pokemon App")
        AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO )
    }
}