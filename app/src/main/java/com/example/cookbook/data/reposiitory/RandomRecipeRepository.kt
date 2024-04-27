package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.datasource.api.GetRandomRecipeResult
import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse

interface RandomRecipeRepository {
    suspend fun getRandomRecipe(apiKey: String, number: Int) : Result<RandomRecipeResponse>
}