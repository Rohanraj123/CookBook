package com.example.cookbook.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookbook.presentation.utils.PreferenceManager
import com.example.cookbook.presentation.view.homescreen.HomeScreen
import com.example.cookbook.presentation.view.LogInScreen
import com.example.cookbook.presentation.view.RegisterScreen
import com.example.cookbook.presentation.view.recipedetailsscreen.RecipeDetailScreen
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.LogInScreenViewModel
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel
import com.example.cookbook.presentation.viewmodel.RegisterScreenViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    registerScreenViewModel: RegisterScreenViewModel,
    logInScreenViewModel: LogInScreenViewModel,
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {

    val context = LocalContext.current

    val preferenceManager = remember {
        PreferenceManager(context)
    }

    val loggedIn = preferenceManager.getBoolean("loggedIn", false)

    NavHost(
        navController = navController,
        startDestination = if (loggedIn) "HomeScreen/{name}" else "logInScreen"
    ) {
        composable("logInScreen") {
            LogInScreen(logInScreenViewModel, navController)
        }
        composable("RegisterScreen/{name}") {backStackEntry ->
            RegisterScreen(registerScreenViewModel, navController, backStackEntry)
        }
        composable("HomeScreen/{name}") {backStackEntry ->
            HomeScreen(navController, homeScreenViewModel, recipeDetailScreenViewModel, backStackEntry)
        }
        composable("RecipeDetailScreen") {backStackEntry ->
            RecipeDetailScreen(recipeDetailScreenViewModel, backStackEntry, navController)
        }
    }
}