package com.example.cookbook.presentation.utils

import com.example.cookbook.data.models.randomrecipemodel.RandomRecipeResponse

sealed class GetRandomRecipeResult {
    data class Success(val data: RandomRecipeResponse) : GetRandomRecipeResult()
    data class Error(val throwable: Throwable) : GetRandomRecipeResult()
}
