package com.example.modernfoodrecipes.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.example.domain.base.ResultDomain
import com.example.domain.models.FoodRecipeDomain
import com.example.domain.usecases.GetHomeRecipesUseCase
import com.example.modernfoodrecipes.data.Repository
import com.example.modernfoodrecipes.data.database.RecipesEntity
import com.example.modernfoodrecipes.models.FoodRecipe
import com.example.modernfoodrecipes.toApp
import com.example.modernfoodrecipes.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val getHomeRecipesUseCase: GetHomeRecipesUseCase,
    application: Application
) : AndroidViewModel(application) {


    /** Room data source  */

    val readRecipes: LiveData<List<RecipesEntity>> = repository.local.readDatabase().asLiveData()

    fun insertRecipe(recipesEntity: RecipesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipes(recipesEntity)
        }
    }


    /** Retrofit data source  */
    val recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) {
        viewModelScope.launch {
//            getRecipesSafeCall(queries)
            getRecipesSafeCallClean(queries)
        }
    }

    private suspend fun getRecipesSafeCallClean(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
//                val response = repository.remote.getRecipes(queries)
                val response = getHomeRecipesUseCase.invoke(queries)

                recipesResponse.value = handleRecipesFoodResponseClean(response)

                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null) {
                    offlineCacheRecipe(foodRecipe)
                }
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  ok ")
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(e.message)
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  failed ")
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is : " + e.message.toString())
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection")
            Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  no connection ")
        }
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleRecipesFoodResponse(response)

                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null) {
                    offlineCacheRecipe(foodRecipe)
                }
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  ok ")
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(e.message)
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  failed ")
                Log.d("getRecipesSafeCall", "getRecipesSafeCall state is : " + e.message.toString())
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection")
            Log.d("getRecipesSafeCall", "getRecipesSafeCall state is :  no connection ")
        }
    }

    private fun offlineCacheRecipe(foodRecipe: FoodRecipe) {
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipe(recipesEntity)
    }

    private fun handleRecipesFoodResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("time out ")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("Api Key limited ! ")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found !")
            }
            response.isSuccessful -> {
                return NetworkResult.Success(response.body()!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleRecipesFoodResponseClean(response: ResultDomain<FoodRecipeDomain>): NetworkResult<FoodRecipe>? {
        when (response) {
            is ResultDomain.Success -> {
                response.data.let {
                    try {
                        val foodRecipe: FoodRecipe = it!!.toApp()
                        return NetworkResult.Success(foodRecipe)
                    } catch (e: Exception) {
                        return NetworkResult.Error(e.message)
                    }
                }
            }
            is ResultDomain.Error -> {
                return NetworkResult.Error(response.message)
            }
            else -> {
                return NetworkResult.Loading()
            }

        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }
}