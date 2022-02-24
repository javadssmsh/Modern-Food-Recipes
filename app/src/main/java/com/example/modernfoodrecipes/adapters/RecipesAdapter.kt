package com.example.modernfoodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.modernfoodrecipes.databinding.RecipesRowLayoutBinding
import com.example.modernfoodrecipes.models.FoodRecipe
import com.example.modernfoodrecipes.models.Result
import com.example.modernfoodrecipes.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private var recipes = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipesViewHolder(private val recipesRowLayoutBinding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(recipesRowLayoutBinding.root) {

        fun bind(result: Result) {
            recipesRowLayoutBinding.result = result
            recipesRowLayoutBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return RecipesViewHolder(binding)
            }
        }
    }

    fun sendData(newRecipe: FoodRecipe) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, newRecipe.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newRecipe.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}