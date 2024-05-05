package com.example.cookbook.data.models.RandomRecipeModel

import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse(
    @SerializedName("recipes") val recipes : List<Recipe>
)