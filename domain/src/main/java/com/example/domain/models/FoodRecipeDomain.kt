package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class FoodRecipeDomain(
    val results: List<ResultDomain>
)
