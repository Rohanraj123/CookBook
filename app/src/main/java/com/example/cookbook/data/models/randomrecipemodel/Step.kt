package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("number"     ) var number      : Int?                  = null,
    @SerializedName("step"       ) var step        : String?               = null,
    @SerializedName("ingredients") var ingredients : ArrayList<String>?    = arrayListOf(),
    @SerializedName("equipment"  ) var equipment   : ArrayList<Ingredient>? = arrayListOf(),
    @SerializedName("length"     )   var length    : Length?
)
