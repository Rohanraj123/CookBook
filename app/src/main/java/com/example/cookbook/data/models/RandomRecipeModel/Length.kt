package com.example.cookbook.data.models.RandomRecipeModel

import com.google.gson.annotations.SerializedName

data class Length(
    @SerializedName("number" ) val number : Int,
    @SerializedName("unit"   ) val unit   : String
)