package com.example.cookbook.data.models.randomrecipemodel

import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("us"    ) var us     : Us,
    @SerializedName("metric") var metric : Measure
)
