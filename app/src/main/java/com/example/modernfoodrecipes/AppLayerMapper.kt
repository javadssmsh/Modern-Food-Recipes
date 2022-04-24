package com.example.modernfoodrecipes

import com.example.domain.models.ExtendedIngredientDomain
import com.example.domain.models.FoodRecipeDomain
import com.example.domain.models.ResultDomain
import com.example.modernfoodrecipes.models.ExtendedIngredient
import com.example.modernfoodrecipes.models.FoodRecipe
import com.example.modernfoodrecipes.models.Result




fun ResultDomain.toApp(): Result {
    return Result(
        aggregateLikes = aggregateLikes,
        cheap = cheap,
        dairyFree = dairyFree,
        extendedIngredients = mapExtendedIngredientDataListToDomain(extendedIngredients),
        glutenFree = glutenFree,
        id = id,
        image = image,
        readyInMinutes = readyInMinutes,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        summary = summary,
        title = title, vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        )
}

fun ExtendedIngredientDomain.toDomain(): ExtendedIngredient {
    return ExtendedIngredient(
        amount = amount,
        consistency = consistency,
        image = image,
        name = name,
        original = original,
        unit = unit
    )
}

fun mapExtendedIngredientDataListToDomain(extendedIngredients: List<ExtendedIngredientDomain>): List<ExtendedIngredient> {
    var extendedIngredientList: ArrayList<ExtendedIngredient> = ArrayList()
    for (extendedIngredient in extendedIngredients) {
        extendedIngredientList.add(extendedIngredient.toDomain())
    }
    return extendedIngredientList
}

fun FoodRecipeDomain.toApp(): FoodRecipe {
    return FoodRecipe(
        results = mapResultDomainToApp(results),
    )
}

fun mapResultDomainToApp(foodRecipeDataList: List<ResultDomain>): List<Result> {
    val foodRecipeList: ArrayList<Result> = ArrayList()
    for (foodRecipe in foodRecipeDataList) {
        foodRecipeList.add(foodRecipe.toApp())
    }
    return foodRecipeList
}