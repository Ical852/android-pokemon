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

        setupBehavior()
        pokemonListInit()
    }

    private fun setupBehavior() {
        viewModel.count.observe(viewLifecycleOwner) {
            binding.loadingCount.text = it.toString()
        }

        viewModel.extendCount.observe(viewLifecycleOwner) {
            binding.extendLoadingCount.text = it.toString()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.mainContent.visibility = View.GONE
                binding.loadingContent.visibility = View.VISIBLE
            }
        }

        viewModel.extendLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadMoreBtn.visibility = View.GONE
                binding.extendLoadingContent.visibility = View.VISIBLE
            }
        }

        viewModel.failed.observe(viewLifecycleOwner) {
            if (it) {
                binding.mainContent.visibility = View.GONE
                binding.retryContent.visibility = View.VISIBLE
            }
        }

        viewModel.extendFailed.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadMoreBtn.visibility = View.GONE
                binding.extendRetryContent.visibility = View.VISIBLE
            }
        }

        binding.retryBtn.setOnClickListener {
            viewModel.fetch()
            binding.retryContent.visibility = View.GONE
        }
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
            val current = it
            binding.loadMoreBtn.setOnClickListener {
                viewModel.fetch("extend", viewModel.pokemons.value!!.next, current)
            }
            binding.retryExtendBtn.setOnClickListener {
                viewModel.fetch("extend", viewModel.pokemons.value!!.next, current)
                binding.extendRetryContent.visibility = View.GONE
            }
            if (it != null) {
                binding.loadingContent.visibility = View.GONE
                binding.extendLoadingContent.visibility = View.GONE
                binding.mainContent.visibility = View.VISIBLE
                binding.loadMoreBtn.visibility = View.VISIBLE
                binding.retryContent.visibility = View.GONE
                binding.extendRetryContent.visibility = View.GONE
                pokemonListAdapter.updateData(it.results!!)
            }
        }
    }

}