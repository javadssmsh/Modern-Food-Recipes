package com.example.domain.usecases

import com.example.domain.repositories.HomeRepository

class GetHomeRecipesUseCase(private val homeRepository: HomeRepository) {

    suspend operator fun invoke( queries: Map<String, String>) =
        homeRepository.getRemoteRecipes(queries)
}