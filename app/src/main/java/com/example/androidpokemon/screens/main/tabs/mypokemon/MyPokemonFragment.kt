package com.example.androidpokemon.screens.main.tabs.mypokemon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidpokemon.adapters.MyPokemonAdapter
import com.example.androidpokemon.databinding.FragmentMyPokemonBinding
import com.example.androidpokemon.models.DetailModel
import com.example.androidpokemon.models.pokemonmodels.PokemonDetailModel
import com.example.androidpokemon.screens.detail.mypokemon.MyPokemonDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val myPokemonModule = module {
    factory { MyPokemonFragment() }
}

class MyPokemonFragment : Fragment() {
    private lateinit var binding: FragmentMyPokemonBinding
    private val viewModel: MyPokemonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPokemonBinding.inflate(layoutInflater, container,  false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBehavior()
        pokemonListInit()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetch()
    }

    private fun setupBehavior() {
        viewModel.count.observe(viewLifecycleOwner) {
            binding.loadingCount.text = it.toString()
        }

        viewModel.maxCount.observe(viewLifecycleOwner) {
            binding.maxLoadingCount.text = it.toString()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.rvPokemon.visibility = View.GONE
                binding.zeroPokemon.visibility = View.GONE
                binding.loadingContent.visibility = View.VISIBLE
            }
        }

        viewModel.failed.observe(viewLifecycleOwner) {
            if (it) {
                binding.rvPokemon.visibility = View.GONE
                binding.zeroPokemon.visibility = View.GONE
                binding.loadingContent.visibility = View.GONE
                binding.retryContent.visibility = View.VISIBLE
            }
        }

        binding.retryBtn.setOnClickListener {
            viewModel.fetch()
            binding.retryContent.visibility = View.GONE
        }
    }

    private val pokemonListAdapter by lazy {
        MyPokemonAdapter(arrayListOf()) { pokemon ->
            fun getImage(detail: PokemonDetailModel): String {
                if (detail.sprites?.other?.showdown?.frontDefault != null) {
                    return detail.sprites.other.showdown.frontDefault;
                }
                if (detail.sprites?.frontDefault != null) {
                    return detail.sprites.frontDefault
                }
                return ""
            }

            val detail = DetailModel(
                getImage(pokemon.detail!!),
                pokemon.nickname,
                pokemon.detail!!.stats!!,
                pokemon.detail!!.types!!,
                "",
                pokemon.id,
            )

            startActivity(
                Intent(requireActivity(), MyPokemonDetailActivity::class.java)
                    .putExtra("pokemon_detail", detail)
            )
        }
    }

    private fun pokemonListInit() {
        binding.rvPokemon.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPokemon.adapter = pokemonListAdapter

        viewModel.pokemons.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.loadingContent.visibility = View.GONE
                binding.retryContent.visibility = View.GONE
                pokemonListAdapter.updateData(it.data!!)
                if (it.data.size < 1) {
                    binding.rvPokemon.visibility = View.GONE
                    binding.zeroPokemon.visibility = View.VISIBLE
                } else {
                    binding.rvPokemon.visibility = View.VISIBLE
                    binding.zeroPokemon.visibility = View.GONE
                }
            }
        }
    }
}