package com.example.cookbook.presentation.view.recipedetailsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun RecipeDetailScreen(
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel,
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(recipeDetailScreenViewModel) {navController.popBackStack()}
    }
}