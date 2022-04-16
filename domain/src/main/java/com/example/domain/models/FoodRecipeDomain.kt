package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class FoodRecipeDomain(
    @SerializedName("results")
    val results: List<ResultDomain>
)
