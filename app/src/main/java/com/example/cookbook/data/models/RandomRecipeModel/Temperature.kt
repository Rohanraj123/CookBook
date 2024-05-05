package com.example.cookbook.data.models.RandomRecipeModel

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("number" ) val number : Double,
    @SerializedName("unit"   ) val unit   : String
)