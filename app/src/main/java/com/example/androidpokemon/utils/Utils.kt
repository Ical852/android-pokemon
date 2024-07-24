package com.example.androidpokemon.utils

import android.graphics.Color
import com.example.androidpokemon.models.getallpokemonmodels.PokemonResultModel
import com.example.androidpokemon.models.getpokemondetailmodels.PokemonDetailModel
import com.example.androidpokemon.models.getpokemonevolutionmodels.GetPokemonEvolutionModel

class Utils {
    fun nullChecker(check: Any?): Any? {
        return check ?: null
    }

    fun isNotNull(check: Any?): Boolean {
        return check != null
    }

    fun capitalize(text: String): String {
        if (text.isEmpty()) return text
        return text[0].uppercase() + text.substring(1)
    }

    val pokeColors = mapOf(
        "normal" to "#B9BBB7",
        "poison" to "#A55C9C",
        "psychic" to "#B9BBB7",
        "grass" to "#8CD550",
        "ground" to "#E8C454",
        "ice" to "#95F1FE",
        "fire" to "#F95542",
        "rock" to "#CFBD73",
        "dragon" to "#8A75FD",
        "water" to "#55AEFE",
        "bug" to "#C3D21F",
        "dark" to "#8D6855",
        "fighting" to "#AD594C",
        "ghost" to "#7875D6",
        "steel" to "#C3C1DA",
        "flying" to "#78A5FF",
        "electric" to "#FBE33B",
        "fairy" to "#FCAEFF"
    )

    fun getPokeColor(type: String): String {
        val normalizedType = type.lowercase()
        return pokeColors[normalizedType] ?: "#B9BBB7"
    }

    fun setupEvolutions(
        evolutions: GetPokemonEvolutionModel,
        results: List<PokemonResultModel>
    ): List<PokemonDetailModel> {
        val setupEvolves = mutableListOf<PokemonDetailModel>()

        val minFind = evolutions.chain?.species?.name
        val midSet = evolutions.chain?.evolvesTo
        var midFind: String? = null
        var maxFind: String? = null

        if (midSet != null && midSet.isNotEmpty()) {
            midFind = midSet.first().species?.name

            val maxSet = midSet.first().evolvesTo
            if (maxSet != null && maxSet.isNotEmpty()) {
                maxFind = maxSet.first().species?.name
            }
        }

        minFind?.let { minName ->
            try {
                val minFinded = results.firstOrNull { it.name == minName }
                minFinded?.detail?.let { setupEvolves.add(it) }
            } catch (e: Exception) {
            }
        }

        midFind?.let { midName ->
            try {
                val midFinded = results.firstOrNull { it.name == midName }
                midFinded?.detail?.let { setupEvolves.add(it) }
            } catch (e: Exception) {
            }
        }

        maxFind?.let { maxName ->
            try {
                val maxFinded = results.firstOrNull { it.name == maxName }
                maxFinded?.detail?.let { setupEvolves.add(it) }
            } catch (e: Exception) {
            }
        }

        return setupEvolves
    }

//    fun getGifList(show: Any): List<Any> {
//        val list = mutableListOf<Any>()
//        val setup = show.toJson()
//
//        list.add(show.frontDefault)
//        for (key in setup.keys) {
//            if (setup[key] != null && key != "frontDefault") {
//                list.add(setup[key]!!)
//            }
//        }
//
//        return list
//    }
}