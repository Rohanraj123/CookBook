package com.example.cookbook.data.datasource.api

import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("recipes/random")
    fun getRandomRecipe(
        @Query("api_key") apiKey: String,
        @Query("number") number: Int
    ): Call<RandomRecipeResponse>
}