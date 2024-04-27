package com.example.cookbook.data.reposiitory

import com.example.cookbook.data.datasource.api.GetRandomRecipeResult
import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

class RandomRecipeRepositoryImpl(
    private val retrofitApi: RetrofitApi
) : RandomRecipeRepository {
    override suspend fun getRandomRecipe(apiKey: String, number: Int):
            Result<RandomRecipeResponse> {
        return try {
            val response = retrofitApi.getRandomRecipe(apiKey, number)
                .awaitResponse()

            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    Result.success(data)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch the network " +
                        "data with status code: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            Result.failure(ioException)
        } catch (httpException: HttpException) {
            httpException.printStackTrace()
            Result.failure(httpException)
        }
    }
}