package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.models.RandomRecipeModel.Recipe

interface GetRecipeInformationRepository {
    suspend fun getRecipeInformation(id: Int, apiKey: String): Result<Recipe>
}