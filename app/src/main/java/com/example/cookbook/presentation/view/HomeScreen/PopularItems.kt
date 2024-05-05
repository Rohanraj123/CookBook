package com.example.cookbook.presentation.view.HomeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.cookbook.R
import com.example.cookbook.data.models.RandomRecipeModel.Recipe
import com.example.cookbook.data.models.SearchRecipe.SearchResult
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun PopularItems(
    popularItems: List<Recipe>,
    searchItems: List<SearchResult>,
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel,
    navController: NavHostController,
    name: String?,
    homeScreenViewModel: HomeScreenViewModel
) {

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ){
        item {HeaderText(name = name)}
        item { CustomSearchBar(navController, homeScreenViewModel) }
        item {
            Text(
                text = "Recipes",
                modifier = Modifier.padding(horizontal = 30.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        items(searchItems) {item ->
            val recipeId = item.id
            val recipeInfo = homeScreenViewModel.recipeInfoMap[recipeId]

            DishCard(
                recipe = recipeInfo,
                onClick = {
                    recipeDetailScreenViewModel.setRecipe(recipeInfo)
                    navController.navigate("RecipeDetailScreen")
                }
            )
        }
        items(popularItems) { recipe ->
            val recipeId = recipe.id
            DishCard(
                recipe = recipe,
                onClick = {
                    recipeDetailScreenViewModel.setRecipe(recipe)
                    navController.navigate("RecipeDetailScreen")
                }
            )
        }
    }
}

@Composable
fun DishCard(
    recipe: Recipe?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                if (recipe != null) {
                    Image(
                        painter = rememberImagePainter(recipe.image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            if (recipe != null) {
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                if (recipe != null) {
                    if (recipe.vegetarian) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    } else {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                if (recipe != null) {
                    Text(
                        text = recipe.readyInMinutes.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}