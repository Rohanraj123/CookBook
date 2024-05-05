package com.example.cookbook.presentation.view.RecipeDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.presentation.viewmodel.RecipeDetailScreenViewModel

@Composable
fun Instructions(
    recipeDetailScreenViewModel: RecipeDetailScreenViewModel
) {
    val selectedRecipe = recipeDetailScreenViewModel
        .selectedRecipe
        .collectAsState()
        .value


    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Instructions",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = selectedRecipe?.instructions.toString(),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Steps",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        val analyzedInstructions = selectedRecipe?.analyzedInstructions
        analyzedInstructions?.forEach { analyzedInstruction ->
            val steps = analyzedInstruction.steps
            steps.forEach { step ->
                Text(
                    text = step.step,
                    color = Color.Gray
                )
            }
        }
    }
}