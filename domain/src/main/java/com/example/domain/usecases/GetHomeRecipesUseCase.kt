package com.example.domain.usecases

import com.example.domain.repositories.HomeRepository
import retrofit2.http.QueryMap

class GetHomeRecipesUseCase(private val homeRepository: HomeRepository) {

    suspend operator fun invoke(@QueryMap queries: Map<String, String>) =
        homeRepository.getRemoteRecipes(queries)
}