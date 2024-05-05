package com.example.cookbook.utils

import com.example.cookbook.data.models.RandomRecipeModel.RandomRecipeResponse

sealed class GetRandomRecipeResult {
    data class Success(val data: RandomRecipeResponse) : GetRandomRecipeResult()
    data class Error(val throwable: Throwable) : GetRandomRecipeResult()
}
