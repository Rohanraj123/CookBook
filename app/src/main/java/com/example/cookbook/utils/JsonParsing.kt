package com.example.cookbook.utils

import com.example.cookbook.data.models.randomrecipemodel.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun parseRecipe(jsonString: String) : List<Recipe> {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<List<Recipe>>() {}.type)
}

// Fetch the recipe in the viewmodel from the repository
// Parse that recipe
// and then use it using lazyColumn