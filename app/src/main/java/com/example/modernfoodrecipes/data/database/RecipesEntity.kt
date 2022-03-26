package com.example.modernfoodrecipes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.modernfoodrecipes.models.FoodRecipe
import com.example.modernfoodrecipes.util.Constants


@Entity(tableName = Constants.RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}