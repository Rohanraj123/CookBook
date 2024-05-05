package com.example.cookbook.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.models.RandomRecipeModel.Recipe
import com.example.cookbook.data.models.SearchRecipe.SearchResult
import com.example.cookbook.data.reposiitory.GetRecipeInformationRepository
import com.example.cookbook.data.reposiitory.RandomRecipeRepository
import com.example.cookbook.data.reposiitory.SearchRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val randomRecipeRepository: RandomRecipeRepository,
    private val searchRecipeRepository: SearchRecipeRepository,
    private val getRecipeInformationRepository: GetRecipeInformationRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _popularItems = MutableStateFlow<List<Recipe>>(emptyList())
    val popularItems: StateFlow<List<Recipe>>
        get() = _popularItems


    private val _searchItems = MutableStateFlow<List<SearchResult>>(emptyList())
    val searchItems: StateFlow<List<SearchResult>>
        get() = _searchItems

    private val _searchedRecipe = MutableStateFlow<Recipe?>(null)
    val searchedRecipe: StateFlow<Recipe?>
        get() = _searchedRecipe

    val recipeInfoMap = mutableMapOf<Int, Recipe?>()

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

    fun searchRecipe(apiKey: String, query: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = searchRecipeRepository.searchRecipe(apiKey, query)
                if (result.isSuccess) {
                    val results = result.getOrNull()?.results ?: emptyList()
                    _searchItems.value = results
                    results.forEach { item ->
                        item.id?.let { getRecipeInformation(it, apiKey) }
                    }
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getRecipeInformation(id: Int, apiKey: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getRecipeInformationRepository.getRecipeInformation(id, apiKey)
                if (result.isSuccess) {
                    recipeInfoMap[id] = result.getOrNull()
                    _searchedRecipe.value = recipeInfoMap[id]
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}