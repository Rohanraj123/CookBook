package com.example.cookbook.data.reposiitory

import android.util.Log
import com.example.cookbook.data.datasource.api.RetrofitApi
import com.example.cookbook.data.models.RandomRecipeModel.Recipe
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

class GetRecipeInformationRepositoryImpl(
    private val retrofitApi: RetrofitApi
): GetRecipeInformationRepository {
    override suspend fun getRecipeInformation(id: Int, apiKey: String): Result<Recipe> {
        return try {

            val response = retrofitApi.getRecipeInformation(id, apiKey).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                Log.d("GetRecipeInfoRepoImpl", "response : ${response.body()}")
                if (data != null) {
                    Result.success(data)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(
                    Exception(
                        "Failed to fetch the network " +
                                "data with status code: ${response.code()}"
                    )
                )
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