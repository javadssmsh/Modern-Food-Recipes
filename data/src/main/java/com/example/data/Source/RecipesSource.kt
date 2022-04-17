package com.example.data.Source

import com.example.data.api.FoodRecipesApiData
import com.example.data.toDomain
import com.example.domain.NetworkResultDomain
import com.example.domain.models.FoodRecipeDomain
import retrofit2.http.QueryMap
import javax.inject.Inject


class RecipesSource @Inject constructor(
    private val apiData: FoodRecipesApiData
) {

    suspend fun loadHomeRecipesFromServer(@QueryMap queries: Map<String, String>): NetworkResultDomain<FoodRecipeDomain> {
        val response = apiData.getRecipes(queries)
        return when {
            response.isSuccessful -> {
                val body: FoodRecipeDomain = response.body()!!.toDomain()
                NetworkResultDomain.Success(body)
            }
            else -> {
                NetworkResultDomain.Error("there is a problem")
            }
        }
    }
}