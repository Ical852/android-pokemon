package com.example.androidpokemon.models.restmodels.response

import java.io.Serializable

data class PostResponseModel(
    val status: Int,
    val message: String
) : Serializable
