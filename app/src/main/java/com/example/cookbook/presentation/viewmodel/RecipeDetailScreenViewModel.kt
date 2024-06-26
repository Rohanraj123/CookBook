package com.example.cookbook.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.example.cookbook.data.models.RandomRecipeModel.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeDetailScreenViewModel @Inject constructor(
): ViewModel() {

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe.asStateFlow()

    fun setRecipe(recipe: Recipe?) {
        _selectedRecipe.value = recipe
    }
}