package com.example.cookbook.presentation.view.homescreen

import android.util.Log
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.data.models.randomrecipemodel.Recipe
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import javax.xml.transform.Result
import kotlin.reflect.KProperty

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {
    val isLoading by homeScreenViewModel.isLoading.collectAsState(initial = false)
    Log.d("HomeScreen", "isLoading status : $isLoading")
    val popularItems by homeScreenViewModel.popularItems.collectAsState(emptyList())
    Log.d("HomeScreen", "list of recipes : $popularItems")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(name = "Rohan")
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Recipes",
            modifier = Modifier.padding(horizontal = 30.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            if (popularItems.isNotEmpty()) {
                PopularItems(popularItems = popularItems)
            } else {
                Text(
                    text = "No recipes found",
                    modifier = Modifier.padding(horizontal = 30.dp),
                    fontSize = 16.sp
                )
            }
        }
    }

    // Fetch random recipes when the screen is first shown
    LaunchedEffect(key1 = Unit) {
        homeScreenViewModel.getRandomRecipe(apiKey = "e0157e0d0d574cefb354163aff9f9056", number = 15)
    }
}