package com.example.modernfoodrecipes.di

import com.example.data.Source.RecipesSource
import com.example.data.api.FoodRecipesApiData
import com.example.data.repositories.HomeRepository
import com.example.domain.usecases.GetHomeRecipesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class HomeBindingModule {
    @Singleton
    @Binds
    abstract fun bindHomeRepository(homeRepository: HomeRepository): com.example.domain.repositories.HomeRepository
}


@Module
@InstallIn(ActivityRetainedComponent::class)
open class HomeModule {
    @Provides
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ) = GetHomeRecipesUseCase(homeRepository)

    @Provides
    fun provideHomeDataSource(
        foodRecipesApiData: FoodRecipesApiData
    ) = RecipesSource(foodRecipesApiData)
}