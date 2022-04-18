package com.example.domain.models


data class ResultDomain(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: List<ExtendedIngredientDomain>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)
