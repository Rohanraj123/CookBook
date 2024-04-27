package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import retrofit2.awaitResponse

class RandomRecipeRepositoryImpl(
    private val retrofitApi: RetrofitApi
) : RandomRecipeRepository {
    /*
    override suspend fun getRandomRecipe(apiKey: String): Result<RandomRecipeResponse> {
        return try {
            val response = retrofitApi.getRandomRecipe(apiKey).awaitResponse()
            Log.d("RepoImpl", "getRandomRecipe is called of retrofitApi")
            Log.d("RepoImpl", "Request Parameters: apiKey - $apiKey")
            Log.d("RepoImpl", "response : ${response.body()}")
            if (response.isSuccessful) {
                Log.d("ResponseStatus", "response status is : ${response.message()}")
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
            e.printStackTrace()
            Log.e("RandomRecipeRepository", "Exception occurred: ${e.message}")
            Result.failure(e)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            Log.e("RandomRecipeRepository", "Exception occurred: ${ioException.message}")
            Result.failure(ioException)
        } catch (httpException: HttpException) {
            httpException.printStackTrace()
            Log.e("RandomRecipeRepository", "Exception occurred: ${httpException.message}")
            Result.failure(httpException)
        }
    }

     */
    override suspend fun getRandomRecipe(apiKey: String): Result<RandomRecipeResponse> {
        return try {
            val response = retrofitApi.getRandomRecipe(apiKey).awaitResponse() // Await the asynchronous response
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch recipe (code: ${response.code()})")) // Include error code
            }
        } catch (e: Exception) {
            Result.failure(e) // Handle other exceptions
        }
    }


}