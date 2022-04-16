package com.example.domain.repositories

import com.example.domain.models.FoodRecipeDomain
import retrofit2.Response
import retrofit2.http.QueryMap

interface HomeRepository {

    suspend fun getRemoteRecipes(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipeDomain>

}