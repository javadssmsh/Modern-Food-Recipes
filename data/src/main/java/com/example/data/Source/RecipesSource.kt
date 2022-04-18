package com.example.data.Source

import com.example.data.api.FoodRecipesApiData
import com.example.data.toDomain
import com.example.domain.base.ResultDomain
import com.example.domain.models.FoodRecipeDomain
import retrofit2.http.QueryMap
import javax.inject.Inject


class RecipesSource @Inject constructor(
    private val apiData: FoodRecipesApiData
) {

    suspend fun loadHomeRecipesFromServer(@QueryMap queries: Map<String, String>): ResultDomain<FoodRecipeDomain> {
        val response = apiData.getRecipes(queries)
        return when {
            response.isSuccessful -> {
                val body: FoodRecipeDomain = response.body()!!.toDomain()
                ResultDomain.Success(body)
            }
            else -> {
                ResultDomain.Error("there is a problem")
            }
        }
    }
}