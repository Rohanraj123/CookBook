package com.example.cookbook.presentation.view.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookbook.R

@Composable
fun CategoriesList(
    categoryName1: String,
    categoryName2: String,
    categoryName3: String,
    categoryName4: String,
    icon1: Painter,
    icon2: Painter,
    icon3: Painter,
    icon4: Painter,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Category(categoryName1, icon1)
        }
        item {
            Category(categoryName2, icon2)
        }
        item {
            Category(categoryName3, icon3)
        }
        item {
            Category(categoryName4, icon4)
        }
    }
}


@Composable
fun Category(
    categoryName: String,
    icon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = categoryName,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
