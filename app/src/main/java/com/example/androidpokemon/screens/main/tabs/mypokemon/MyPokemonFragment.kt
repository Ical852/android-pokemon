package com.example.androidpokemon.screens.main.tabs.mypokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpokemon.R
import com.example.androidpokemon.databinding.FragmentHomeBinding
import com.example.androidpokemon.databinding.FragmentMyPokemonBinding
import org.koin.dsl.module

val myPokemonModule = module {
    factory { MyPokemonFragment() }
}

class MyPokemonFragment : Fragment() {
    private lateinit var binding: FragmentMyPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPokemonBinding.inflate(layoutInflater, container,  false)
        return binding.root
    }
}