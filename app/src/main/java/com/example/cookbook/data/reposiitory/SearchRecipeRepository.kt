package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.models.SearchRecipe.SearchRecipeResponse

interface SearchRecipeRepository {
    suspend fun searchRecipe(apiKey: String, query: String): Result<SearchRecipeResponse>;
}