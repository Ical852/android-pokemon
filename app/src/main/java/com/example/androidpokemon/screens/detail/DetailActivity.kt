package com.example.androidpokemon.screens.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.androidpokemon.R
import com.example.androidpokemon.databinding.ActivityDetailBinding
import com.example.androidpokemon.databinding.StatIndicatorBinding
import com.example.androidpokemon.models.pokemonmodels.DetailModel
import com.example.androidpokemon.models.pokemonmodels.StatsModel
import com.example.androidpokemon.models.pokemonmodels.TypeModel
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

        binding.catchBtn.setOnClickListener {
            onCatch()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }

        val detail = intent.getSerializableExtra("pokemon_detail") as DetailModel?
        if (detail != null) {
            var color = Color.parseColor(
                Utils().getPokeColor(detail.types[0].type!!.name!!)
            )

            binding.main.setBackgroundColor(color)
            binding.catchBtn.setBackgroundColor(color)
            binding.pokemonName.text = Utils().capitalize(detail.name)
            Glide.with(this@DetailActivity)
                .load(detail.img)
                .into(binding.pokemonImg)
            initTypes(detail.types)
            initIndicator(detail)
        }
    }

    private fun initTypes(types: List<TypeModel>) {
        val type1Drawable = ContextCompat.getDrawable(
            this, R.drawable.pokemon_type_bg
        ) as GradientDrawable
        type1Drawable.setColor(Color.parseColor(Utils().getPokeColor(
            types[0].type!!.name!!
        )))
        binding.firstBadge.root.background = type1Drawable
        binding.firstBadge.typeText.text = types[0].type!!.name!!

        if (types.size > 1) {
            val type2Drawable = ContextCompat.getDrawable(
                this, R.drawable.pokemon_type_bg
            ) as GradientDrawable
            type2Drawable.setColor(Color.parseColor(Utils().getPokeColor(
                types[1].type!!.name!!
            )))
            binding.secondBadge.root.background = type2Drawable
            binding.secondBadge.typeText.text = types[1].type!!.name!!
        } else {
            binding.secondBadge.root.visibility = View.GONE
        }
    }

    private fun initIndicator(detail: DetailModel) {
        var color = Color.parseColor(
            Utils().getPokeColor(detail.types[0].type!!.name!!)
        )

        binding.hpIndicator.tvStat.text = "Hp"
        binding.atkIndicator.tvStat.text = "Attack"
        binding.defIndicator.tvStat.text = "Defense"
        binding.spAtkIndicator.tvStat.text = "Sp. Atk"
        binding.spDefIndicator.tvStat.text = "Sp. Def"
        binding.spdIndicator.tvStat.text = "Speed"
        binding.totalIndicator.tvStat.text = "Total"

        binding.totalIndicator.statusMeter.visibility = View.GONE
        binding.totalIndicator.tvValue.text = getTotal(detail.baseStats).toString()

        binding.hpIndicator.tvValue.text = getValue(detail.baseStats[0].baseStat!!).toString()
        binding.atkIndicator.tvValue.text = getValue(detail.baseStats[1].baseStat!!).toString()
        binding.defIndicator.tvValue.text = getValue(detail.baseStats[2].baseStat!!).toString()
        binding.spAtkIndicator.tvValue.text = getValue(detail.baseStats[3].baseStat!!).toString()
        binding.spDefIndicator.tvValue.text = getValue(detail.baseStats[4].baseStat!!).toString()
        binding.spdIndicator.tvValue.text = getValue(detail.baseStats[5].baseStat!!).toString()

        setProgressColor(binding.hpIndicator, color)
        setProgressColor(binding.atkIndicator, color)
        setProgressColor(binding.defIndicator, color)
        setProgressColor(binding.spAtkIndicator, color)
        setProgressColor(binding.spDefIndicator, color)
        setProgressColor(binding.spdIndicator, color)

        binding.hpIndicator.statusMeter.progress = getValue(detail.baseStats[0].baseStat!!)
        binding.atkIndicator.statusMeter.progress = getValue(detail.baseStats[1].baseStat!!)
        binding.defIndicator.statusMeter.progress = getValue(detail.baseStats[2].baseStat!!)
        binding.spAtkIndicator.statusMeter.progress = getValue(detail.baseStats[3].baseStat!!)
        binding.spDefIndicator.statusMeter.progress = getValue(detail.baseStats[4].baseStat!!)
        binding.spdIndicator.statusMeter.progress = getValue(detail.baseStats[5].baseStat!!)
    }

    private fun setProgressColor(indicatorBinding: StatIndicatorBinding, color: Int) {
        val progressDrawable: Drawable = indicatorBinding.statusMeter.progressDrawable.mutate()
        if (progressDrawable is LayerDrawable) {
            val progressLayer = progressDrawable.findDrawableByLayerId(android.R.id.progress)
            progressLayer?.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
        }
        binding.hpIndicator.statusMeter.progressDrawable = progressDrawable
    }

    private fun getTotal(stats: List<StatsModel>): Int {
        var total: Int = 0
        for (stat in stats) {
            total+= stat.baseStat!!
        }
        return total
    }

    private fun getValue(baseStat: Int): Int {
        if (baseStat > 100) return 100
        return baseStat
    }

    private fun onCatch(): Unit {

    }
}