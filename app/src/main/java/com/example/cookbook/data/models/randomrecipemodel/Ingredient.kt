package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("id"           ) var id            : Int?    = null,
    @SerializedName("name"         ) var name          : String? = null,
    @SerializedName("localizedName") var localizedName : String? = null,
    @SerializedName("image"        ) var image         : String? = null,
)
