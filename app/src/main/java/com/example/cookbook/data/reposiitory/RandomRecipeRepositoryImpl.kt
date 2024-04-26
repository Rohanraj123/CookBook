package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import com.example.cookbook.data.models.randomrecipemodel.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import java.io.IOException

class RandomRecipeRepositoryImpl(
    private val retrofitApi: RetrofitApi
) : RandomRecipeRepository {
    override suspend fun getRandomRecipe(apiKey: String, number: Int): Result<RandomRecipeResponse> {
        return try {
            val response = retrofitApi.getRandomRecipe(apiKey, number).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    Result.success(data)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch the network data with status code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}