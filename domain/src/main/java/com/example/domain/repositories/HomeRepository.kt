package com.example.domain.repositories

import com.example.domain.base.ResultDomain
import com.example.domain.models.FoodRecipeDomain

interface HomeRepository {

    suspend fun getRemoteRecipes(queries: Map<String, String>): ResultDomain<FoodRecipeDomain>

}