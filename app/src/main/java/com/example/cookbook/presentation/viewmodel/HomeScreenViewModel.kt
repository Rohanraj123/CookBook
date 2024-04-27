package com.example.cookbook.presentation.viewmodel

import android.util.Log
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

    fun getRandomRecipe(apiKey: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = randomRecipeRepository.getRandomRecipe(apiKey)
                Log.d("HomeScreenViewModel", "getRandomRecipe of repository is being called with apiKey : $apiKey")
                if (result.isSuccess) {
                    val recipes = result.getOrNull()?.recipes ?: emptyList()
                    _popularItems.value = recipes
                    Log.d("HomeScreenViewModel", "_popularItems.value : ${_popularItems.value}")
                } else {
                    // Handle error (you can expose this via another state or event)
                }
            } catch (e: Exception) {
                // Handle network or other errors
            } finally {
                _isLoading.value = false
            }
        }
    }
}