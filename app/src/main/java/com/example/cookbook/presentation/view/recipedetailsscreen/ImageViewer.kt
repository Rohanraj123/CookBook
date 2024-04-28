package com.example.cookbook.presentation.view.recipedetailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun ImageViewer(
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel
) {
    val selectedRecipe = recipeDetailScreenViewModel.selectedRecipe.collectAsState().value
    val image = selectedRecipe?.image

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = rememberImagePainter(image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}