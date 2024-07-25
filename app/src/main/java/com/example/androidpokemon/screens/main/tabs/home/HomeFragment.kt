package com.example.androidpokemon.screens.main.tabs.home

import PokemonCardAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidpokemon.databinding.FragmentHomeBinding
import com.example.androidpokemon.screens.detail.DetailActivity
import org.koin.dsl.module
import org.koin.androidx.viewmodel.ext.android.viewModel

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container,  false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonListInit()
    }

    private val pokemonListAdapter by lazy {
        PokemonCardAdapter(arrayListOf()) { pokemon ->
            startActivity(
                Intent(requireActivity(), DetailActivity::class.java)
                    .putExtra("pokemon_detail", pokemon)
            )
        }
    }

    private fun pokemonListInit() {
        binding.rvPokemon.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPokemon.adapter = pokemonListAdapter

        viewModel.pokemons.observe(viewLifecycleOwner) {
            if (it != null) {
                pokemonListAdapter.updateData(it.results!!)
            }
        }
    }

}