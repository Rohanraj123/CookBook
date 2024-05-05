package com.example.cookbook.data.models.SearchRecipe

import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("id"        ) val id        : Int?,
    @SerializedName("title"     ) val title     : String?,
    @SerializedName("image"     ) val image     : String?,
    @SerializedName("imageType" ) val imageType : String?
)
