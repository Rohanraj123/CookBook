package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.models.RandomRecipeModel.RandomRecipeResponse

interface RandomRecipeRepository {
    suspend fun getRandomRecipe(apiKey: String, number: Int): Result<RandomRecipeResponse>
}