package com.example.cookbook.presentation.view.recipedetailsscreen

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel
import com.example.cookbook.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    recipeDetailsScreenViewModel: RecipeDetailScreenViewModel,
    onBackClick: () -> Unit
) {
    val selectedRecipe = recipeDetailsScreenViewModel.selectedRecipe.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                                 IconButton(onClick = { onBackClick() }) {
                                     androidx.compose.material3.Icon(
                                         Icons.Default.ArrowBack,
                                         contentDescription = null
                                     )
                                 }
                },
                title = { Text(text = "Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ButtonColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        if (selectedRecipe != null) {
            Column(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )) {
                ScrollContent(innerPadding)
                Name(recipeDetailsScreenViewModel)
                ImageViewer(recipeDetailsScreenViewModel)
                Informations(recipeDetailsScreenViewModel)
                Instructions(recipeDetailsScreenViewModel)
            }
        } else {
            // Show loading indicator while fetching data
            Text("Loading recipe details...")
        }
    }
}

@Composable
fun ScrollContent(padding: PaddingValues) {}

@Composable
fun Name(recipeDetailsScreenViewModel: RecipeDetailScreenViewModel) {
    val selectedRecipe = recipeDetailsScreenViewModel.selectedRecipe.collectAsState().value
    val title = selectedRecipe?.title

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Name",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title.toString(),
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
}