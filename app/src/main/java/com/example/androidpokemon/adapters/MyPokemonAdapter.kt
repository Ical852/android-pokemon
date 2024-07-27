package com.example.androidpokemon.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidpokemon.R
import com.example.androidpokemon.databinding.ItemPokemonCardBinding
import com.example.androidpokemon.models.pokemonmodels.PokemonResultModel
import com.example.androidpokemon.models.restmodels.response.MyPokemonModel
import com.example.androidpokemon.utils.Utils

class MyPokemonAdapter(
    private val pokemons: ArrayList<MyPokemonModel>,
    private val onClick: (MyPokemonModel) -> Unit
): RecyclerView.Adapter<MyPokemonAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemPokemonCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder (
        ItemPokemonCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons.getOrNull(position)
        if (pokemon == null) return
        val types = pokemon.detail?.types

        val drawable = ContextCompat.getDrawable(
            holder.itemView.context, R.drawable.item_pokemon_bg
        ) as GradientDrawable
        drawable.setColor(
            Color.parseColor(
                Utils().getPokeColor(
            pokemon.detail!!.types!![0].type!!.name!!
        )))
        holder.binding.pokemonCard.background = drawable

        val type1Drawable = ContextCompat.getDrawable(
            holder.itemView.context, R.drawable.pokemon_type_bg
        ) as GradientDrawable
        type1Drawable.setColor(
            Color.parseColor(
                Utils().getPokeColor(
            pokemon.detail!!.types!![0].type!!.name!!
        )))
        holder.binding.firstBadge.root.background = type1Drawable
        holder.binding.firstBadge.typeText.text = pokemon.detail!!.types!![0].type!!.name!!

        if (types!!.size > 1) {
            val type2Drawable = ContextCompat.getDrawable(
                holder.itemView.context, R.drawable.pokemon_type_bg
            ) as GradientDrawable
            type2Drawable.setColor(
                Color.parseColor(
                    Utils().getPokeColor(
                pokemon.detail!!.types!![1].type!!.name!!
            )))
            holder.binding.secondBadge.root.background = type2Drawable
            holder.binding.secondBadge.typeText.text = pokemon.detail!!.types!![1].type!!.name!!
        } else {
            holder.binding.secondBadge.root.visibility = View.GONE
        }

        fun getImage(): String {
            if (pokemon.detail!!.sprites!!.other!!.showdown!!.frontDefault != null) {
                return pokemon.detail!!.sprites!!.other!!.showdown!!.frontDefault!!;
            }
            return pokemon.detail!!.sprites!!.frontDefault!!;
        }

        Glide.with(holder.itemView.context)
            .load(getImage())
            .into(holder.binding.pokemonImg)

        holder.binding.pokemonName.text = Utils().capitalize(pokemon.nickname!!)
        holder.itemView.setOnClickListener { onClick(pokemon) }
    }

    override fun getItemCount() = pokemons.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<MyPokemonModel>) {
        pokemons.clear()
        pokemons.addAll(data)
        notifyDataSetChanged()
    }
}