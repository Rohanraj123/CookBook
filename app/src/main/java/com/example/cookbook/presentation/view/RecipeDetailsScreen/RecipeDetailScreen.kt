package com.example.cookbook.presentation.view.RecipeDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun RecipeDetailScreen(
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(recipeDetailScreenViewModel) {navController.popBackStack()}
    }
}