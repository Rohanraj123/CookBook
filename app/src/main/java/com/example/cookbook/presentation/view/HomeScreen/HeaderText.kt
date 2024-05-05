package com.example.cookbook.presentation.view.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R

@Composable
fun HeaderText(name: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.header_text1),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
        Text(
            text = stringResource(id = R.string.header_text2),
            color = Color.Gray,
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.header_text3),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}
