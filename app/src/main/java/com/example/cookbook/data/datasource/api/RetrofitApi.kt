package com.example.cookbook.data.datasource.api

import com.example.cookbook.data.models.RandomRecipeModel.RandomRecipeResponse
import com.example.cookbook.data.models.RandomRecipeModel.Recipe
import com.example.cookbook.data.models.SearchRecipe.SearchRecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): Call<RandomRecipeResponse>

    @GET("complexSearch")
    fun searchRecipe(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String
    ): Call<SearchRecipeResponse>

    @GET("{id}/information")
    fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Call<Recipe>
}