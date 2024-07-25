package com.example.androidpokemon.screens.detail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidpokemon.R
import com.example.androidpokemon.databinding.ActivityDetailBinding
import com.example.androidpokemon.models.getallpokemonmodels.PokemonResultModel
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel
import com.example.androidpokemon.utils.Utils

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val detail = intent.getSerializableExtra("pokemon_detail") as PokemonDetailModel?

        if (detail != null) {
            binding.main.setBackgroundColor(Color.parseColor(
                Utils().getPokeColor(detail.types!![0].type!!.name!!)
            ))
        }
    }
}