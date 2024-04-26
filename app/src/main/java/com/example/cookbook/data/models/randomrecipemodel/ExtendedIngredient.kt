package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("id"          ) var id           : Int?               = null,
    @SerializedName("aisle"       ) var aisle        : String?            = null,
    @SerializedName("image"       ) var image        : String?            = null,
    @SerializedName("consistency" ) var consistency  : String?            = null,
    @SerializedName("name"        ) var name         : String?            = null,
    @SerializedName("nameClean"   ) var nameClean    : String?            = null,
    @SerializedName("original"    ) var original     : String?            = null,
    @SerializedName("originalName") var originalName : Int?               = null,
    @SerializedName("amount"      ) var amount       : Double?            = null,
    @SerializedName("unit"        ) var unit         : String?            = null,
    @SerializedName("meta"        ) var meta         : ArrayList<String>? = null,
    @SerializedName("measures"    ) var measures     : Measures?          = null
)
