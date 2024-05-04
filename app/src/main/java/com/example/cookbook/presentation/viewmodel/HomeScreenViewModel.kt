package com.example.cookbook.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.models.randomrecipemodel.Recipe
import com.example.cookbook.data.reposiitory.RandomRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val randomRecipeRepository: RandomRecipeRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _popularItems = MutableStateFlow<List<Recipe>>(emptyList())
    val popularItems: StateFlow<List<Recipe>>
        get() = _popularItems


    fun getRandomRecipe(apiKey: String, number: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = randomRecipeRepository.getRandomRecipe(apiKey, number)
                if (result.isSuccess) {
                    val recipes = result.getOrNull()?.recipes ?: emptyList()
                    _popularItems.value = recipes
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}