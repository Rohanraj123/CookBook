package com.example.cookbook.presentation.view.RecipeDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.data.models.RandomRecipeModel.Recipe

@Composable
fun Ingredients(
    selectedRecipe: Recipe
) {
    val ingredients = selectedRecipe.extendedIngredients
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Ingredients",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        ingredients.forEach {ingredient ->
            val quantity = if (ingredient.meta.isNotEmpty()) ingredient.meta[0] else ""
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "$quantity ${ingredient.name}",
                    color = Color.Gray
                )
                Text(
                    text = "- $quantity ${ingredient.amount}",
                    color = Color.Gray
                )
            }
        }
    }
}