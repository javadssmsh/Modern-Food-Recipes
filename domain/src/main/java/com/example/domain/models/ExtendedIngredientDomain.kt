package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class ExtendedIngredientDomain(
    val amount: Double,
    val consistency: String,
    val image: String,
    val name: String,
    val original: String,
    val unit: String
)
