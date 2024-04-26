package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import retrofit2.Callback

interface RandomRecipeRepository {
    suspend fun getRandomRecipe(apiKey: String, number: Int) : Result<RandomRecipeResponse>
}