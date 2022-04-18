package com.example.data.repositories

import com.example.data.Source.RecipesSource
import com.example.domain.base.ResultDomain
import com.example.domain.models.FoodRecipeDomain
import com.example.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeRepository @Inject constructor(private val recipesSource: RecipesSource) :
    HomeRepository {
    override suspend fun getRemoteRecipes(queries: Map<String, String>): ResultDomain<FoodRecipeDomain> {
        return recipesSource.loadHomeRecipesFromServer(queries)
    }

}