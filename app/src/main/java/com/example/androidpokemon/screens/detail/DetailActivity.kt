package com.example.androidpokemon.screens.detail

import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.androidpokemon.databinding.ActivityDetailBinding
import com.example.androidpokemon.databinding.StatIndicatorBinding
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel
import com.example.androidpokemon.screens.main.tabs.home.DetailViewModel
import com.example.androidpokemon.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailActivity() }
}

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finishAffinity()
        }
        binding.catchBtn.setOnClickListener {
            onCatch()
        }

        val detail = intent.getSerializableExtra("pokemon_detail") as PokemonDetailModel?
        if (detail != null) {
            binding.main.setBackgroundColor(Color.parseColor(
                Utils().getPokeColor(detail.types!![0].type!!.name!!)
            ))
            binding.pokemonName.text = detail.name
            Glide.with(this)
                .load(getImage(detail))
                .into(binding.pokemonImg)
            initIndicator(detail)
        }
    }

    private fun getImage(detail: PokemonDetailModel): String {
        if (detail.sprites!!.other!!.showdown!!.frontDefault != null) {
            return detail.sprites.other!!.showdown!!.frontDefault!!;
        }
        return detail.sprites.frontDefault!!;
    }

    private fun onCatch(): Unit {

    }

    private fun getTotal(detail: PokemonDetailModel): Int {
        var total: Int = 0
        for (stat in detail.stats!!) {
            total+= stat.baseStat!!
        }
        return total
    }

    private fun initIndicator(detail: PokemonDetailModel) {
        var color = Color.parseColor(
            Utils().getPokeColor(detail.types!![0].type!!.name!!)
        )

        setProgressBarColor(binding.hpIndicator, color)
        setProgressBarColor(binding.atkIndicator, color)
        setProgressBarColor(binding.defIndicator, color)
        setProgressBarColor(binding.spAtkIndicator, color)
        setProgressBarColor(binding.spDefIndicator, color)
        setProgressBarColor(binding.spdIndicator, color)
        setProgressBarColor(binding.totalIndicator, color)

        binding.hpIndicator.tvStat.text = "Hp"
        binding.atkIndicator.tvStat.text = "Attack"
        binding.defIndicator.tvStat.text = "Defense"
        binding.spAtkIndicator.tvStat.text = "Sp. Atk"
        binding.spDefIndicator.tvStat.text = "Sp. Def"
        binding.spdIndicator.tvStat.text = "Speed"
        binding.totalIndicator.tvStat.text = "Total"

        binding.totalIndicator.statusMeter.visibility = View.GONE
        binding.totalIndicator.tvValue.text = getTotal(detail).toString()

        binding.hpIndicator.tvValue.text = getValue(detail.stats!![0].baseStat!!).toString()
        binding.atkIndicator.tvValue.text = getValue(detail.stats[1].baseStat!!).toString()
        binding.defIndicator.tvValue.text = getValue(detail.stats[2].baseStat!!).toString()
        binding.spAtkIndicator.tvValue.text = getValue(detail.stats[3].baseStat!!).toString()
        binding.spDefIndicator.tvValue.text = getValue(detail.stats[4].baseStat!!).toString()
        binding.spdIndicator.tvValue.text = getValue(detail.stats[5].baseStat!!).toString()

        binding.hpIndicator.statusMeter.progress = getValue(detail.stats!![0].baseStat!!)
        binding.atkIndicator.statusMeter.progress = getValue(detail.stats[1].baseStat!!)
        binding.defIndicator.statusMeter.progress = getValue(detail.stats[2].baseStat!!)
        binding.spAtkIndicator.statusMeter.progress = getValue(detail.stats[3].baseStat!!)
        binding.spDefIndicator.statusMeter.progress = getValue(detail.stats[4].baseStat!!)
        binding.spdIndicator.statusMeter.progress = getValue(detail.stats[5].baseStat!!)
    }

    private fun setProgressBarColor(viewBinding: StatIndicatorBinding, colorResId: Int) {
        val progressBar = viewBinding.statusMeter
        val layerDrawable = progressBar.progressDrawable as LayerDrawable
        val progressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress) as ClipDrawable
        progressDrawable.setColorFilter(
            ContextCompat.getColor(this, colorResId),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun getValue(baseStat: Int): Int {
        if (baseStat > 100) return 100
        return baseStat
    }
}