package com.example.modernfoodrecipes.models

import com.example.foody.models.Result
import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)