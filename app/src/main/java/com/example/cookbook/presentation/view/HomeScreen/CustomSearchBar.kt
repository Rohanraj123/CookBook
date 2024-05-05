package com.example.cookbook.presentation.view.HomeScreen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.presentation.viewmodel.HomeScreenViewModel

@Composable
fun CustomSearchBar(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {
    val apiKey = stringResource(id = R.string.api_key)
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .clickable {
                       Log.d("CustomSearchBar", "Search Bar is clicked")
            },
        contentAlignment = Alignment.CenterStart
    ) {

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it  },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                              },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_bar_placeholder),
                        color = Color.Gray,
                        modifier = Modifier.padding(1.dp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        homeScreenViewModel.searchRecipe(apiKey, searchText)
                        Log.d("CustomSearchBar", "searchRecipe method of homeScreenViewModel is called with api: $apiKey, searchText: $searchText")
                    }
                ),
                shape = RoundedCornerShape(16.dp)
            )
    }
}