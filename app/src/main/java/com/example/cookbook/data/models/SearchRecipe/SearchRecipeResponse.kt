package com.example.cookbook.data.models.SearchRecipe

import com.google.gson.annotations.SerializedName

data class SearchRecipeResponse(

    @SerializedName("results"      ) val results      : List<SearchResult>?,
    @SerializedName("offset"       ) val offset       : Int?,
    @SerializedName("number"       ) val number       : Int?,
    @SerializedName("totalResults" ) val totalResults : Long?
)
