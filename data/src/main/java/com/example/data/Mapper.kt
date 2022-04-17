package com.example.data

import com.example.data.models.ExtendedIngredientData
import com.example.data.models.FoodRecipeData
import com.example.data.models.ResultData
import com.example.domain.models.ExtendedIngredientDomain
import com.example.domain.models.FoodRecipeDomain
import com.example.domain.models.ResultDomain


fun ResultData.toDomain(): ResultDomain {
    return ResultDomain(
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

fun ExtendedIngredientData.toDomain(): ExtendedIngredientDomain {
    return ExtendedIngredientDomain(
        amount = amount,
        consistency = consistency,
        image = image,
        name = name,
        original = original,
        unit = unit
    )
}

fun mapExtendedIngredientDataListToDomain(extendedIngredients: List<ExtendedIngredientData>): List<ExtendedIngredientDomain> {
    var extendedIngredientDomain: ArrayList<ExtendedIngredientDomain> = ArrayList()
    for (extendedIngredient in extendedIngredients) {
        extendedIngredientDomain.add(extendedIngredient.toDomain())
    }
    return extendedIngredientDomain
}

fun FoodRecipeData.toDomain(): FoodRecipeDomain {
    return FoodRecipeDomain(
        results = mapResultDataToDomain(results),
    )
}

fun mapResultDataToDomain(foodRecipeDataList: List<ResultData>): List<ResultDomain> {
    val foodRecipeDomainList: ArrayList<ResultDomain> = ArrayList()
    for (foodRecipe in foodRecipeDataList) {
        foodRecipeDomainList.add(foodRecipe.toDomain())
    }
    return foodRecipeDomainList
}
