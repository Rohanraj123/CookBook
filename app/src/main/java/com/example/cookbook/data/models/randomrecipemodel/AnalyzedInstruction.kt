package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class AnalyzedInstruction(
    @SerializedName("name" ) var name  : String?          = null,
    @SerializedName("steps") var steps : ArrayList<Step> = arrayListOf()
)
