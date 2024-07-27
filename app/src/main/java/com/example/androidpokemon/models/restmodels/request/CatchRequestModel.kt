package com.example.androidpokemon.models.restmodels.request

import java.io.Serializable

data class CatchRequestModel(
    val nickname: String,
    val url: String
) : Serializable
