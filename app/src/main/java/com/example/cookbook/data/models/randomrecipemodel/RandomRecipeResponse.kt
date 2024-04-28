package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse(
    @SerializedName("recipes") val recipes : List<Recipe>
)