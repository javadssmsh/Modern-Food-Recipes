package com.example.data.models

import com.google.gson.annotations.SerializedName

data class FoodRecipeData(
    @SerializedName("results")
    val results: List<ResultData>
)
