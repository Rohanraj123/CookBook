package com.example.cookbook.presentation.view.recipedetailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel
import com.example.cookbook.ui.theme.BoxColor
import com.example.cookbook.ui.theme.BoxTextColor

@Composable
fun Informations(
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel
) {
    val selectedRecipe = recipeDetailScreenViewModel.selectedRecipe.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(BoxColor)
    ) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "Information",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Number of Servings",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.servings.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Price Per Servings",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.pricePerServing.toString() + "$",
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cooking Minutes",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.cookingMinutes.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dairy Free ?",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.dairyFree.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Gluten Free ?",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.glutenFree.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Health Score",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.healthScore.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Preparation Minutes",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.preparationMinutes.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Ready in Minutes",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.servings.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Vegan",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.vegan.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Vegetarian",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.vegetarian.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Very Healthy ?",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.veryHealthy.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Very Popular",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.veryPopular.toString(),
                color = BoxTextColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Read more",
                color = BoxTextColor
            )
            Text(
                text = selectedRecipe?.sourceUrl.toString(),
                color = BoxTextColor
            )
            }
        }
    }
}