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
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.LogInScreenViewModel
import com.example.cookbook.presentation.viewmodel.RegisterScreenViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    registerScreenViewModel: RegisterScreenViewModel,
    logInScreenViewModel: LogInScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {

    val context = LocalContext.current

    val preferenceManager = remember {
        PreferenceManager(context)
    }

    val loggedIn = preferenceManager.getBoolean("loggedIn", false)

    NavHost(
        navController = navController,
        startDestination = if (loggedIn) "HomeScreen" else "logInScreen"
    ) {
        composable("logInScreen") {
            LogInScreen(logInScreenViewModel, navController)
        }
        composable("RegisterScreen") {
            RegisterScreen(registerScreenViewModel, navController)
        }
        composable("HomeScreen") {
            HomeScreen(navController, homeScreenViewModel)
        }
    }
}