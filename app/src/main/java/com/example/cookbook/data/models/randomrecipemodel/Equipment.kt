package com.example.cookbook.data.models.randomrecipemodel

data class Equipment(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String,
    val temperature: Temperature
)