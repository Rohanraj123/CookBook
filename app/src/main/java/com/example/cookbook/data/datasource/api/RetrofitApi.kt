package com.example.cookbook.data.datasource.api

import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API endpoints for the CookBook application.
 */
interface RetrofitApi {

    /**
    * Fetches a random recipe from the Spoonacular API
     *
     * @param apiKey The API key to use for authentication.
     * @return A Call object containing the asynchronous response data.
     */
    @GET("random")
    fun getRandomRecipe(
        @Query("api_key") apiKey: String
    ): Call<RandomRecipeResponse>
}