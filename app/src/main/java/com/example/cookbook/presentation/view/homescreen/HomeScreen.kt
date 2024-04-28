package com.example.cookbook.presentation.view.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.example.cookbook.R
import com.example.cookbook.presentation.view.components.SideNavDrawer
import com.example.cookbook.presentation.view.recipedetailsscreen.ScrollContent
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel
import com.example.cookbook.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
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

    var isDrawerOpen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        isDrawerOpen = !isDrawerOpen
                    }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                },
                title = { Text(text = "CookBook") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ButtonColor,
                    titleContentColor = Color.White
                )
            )
        },
    ) { innerPadding ->
        ScrollContent(padding = innerPadding)
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
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
            if (isDrawerOpen) {
                SideNavDrawer(
                    isDrawerOpen = isDrawerOpen,
                    modifier = Modifier.padding(innerPadding),
                    navController
                ) { drawerState ->
                    isDrawerOpen = drawerState
                }
            }
        }
    }

    val apiKey = stringResource(id = R.string.api_key)
    LaunchedEffect(key1 = Unit) {
        homeScreenViewModel.getRandomRecipe(apiKey = apiKey, number = 20)
    }
}
