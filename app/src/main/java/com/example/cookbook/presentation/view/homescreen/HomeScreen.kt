package com.example.cookbook.presentation.view.homescreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.example.cookbook.R
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel,
    backStackEntry: NavBackStackEntry
) {
    val isLoading by homeScreenViewModel.isLoading.collectAsState(initial = false)
    val popularItems by homeScreenViewModel.popularItems.collectAsState(emptyList())

    val name = backStackEntry.arguments?.getString("name")

    Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp).align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                if (popularItems.isNotEmpty()) {
                    PopularItems(
                        popularItems = popularItems,
                        recipeDetailScreenViewModel,
                        navController,
                        name
                    )
                } else {
                    Text(
                        text = "No recipes found",
                        modifier = Modifier.padding(horizontal = 30.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    val apiKey = stringResource(id = R.string.api_key)
    LaunchedEffect(key1 = Unit) {
        homeScreenViewModel.getRandomRecipe(apiKey = apiKey, number = 20)
    }
}