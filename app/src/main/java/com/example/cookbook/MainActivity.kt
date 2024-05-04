package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.view.navigation.Navigation
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.LogInScreenViewModel
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel
import com.example.cookbook.presentation.viewmodel.RegisterScreenViewModel
import com.example.cookbook.ui.theme.CookBookTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {

                val navController = rememberNavController()

                val registerScreenViewModel = hiltViewModel<RegisterScreenViewModel>()
                val logInScreenViewModel = hiltViewModel<LogInScreenViewModel>()
                val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                val recipeDetailScreenViewModel = hiltViewModel<RecipeDetailScreenViewModel>()

                logInScreenViewModel.setNavController(navController)

                Navigation(
                    navController = navController,
                    registerScreenViewModel = registerScreenViewModel,
                    logInScreenViewModel = logInScreenViewModel,
                    recipeDetailScreenViewModel,
                    homeScreenViewModel
                )
            }
        }
    }
}