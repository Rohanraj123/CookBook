package com.example.cookbook.data.reposiitory

import android.util.Log
import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.models.SearchRecipe.SearchRecipeResponse
import retrofit2.awaitResponse

class SearchRecipeRepositoryImpl(
    private val retrofitApi: RetrofitApi
): SearchRecipeRepository {
    override suspend fun searchRecipe(apiKey: String, query: String)
    : Result<SearchRecipeResponse> {
        return try {
            val response = retrofitApi.searchRecipe(apiKey, query).awaitResponse()
            Log.d("SearchRecipeRepositoryImpl", "response = ${response.body()}")

            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    Result.success(data)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            }
            else {
                Result.failure(Exception("Failed to fetch the network " +
                        "data with status code: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}