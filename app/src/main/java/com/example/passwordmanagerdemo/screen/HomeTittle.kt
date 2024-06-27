package com.example.passwordmanagerdemo.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerdemo.ui.theme.Pink80
import com.example.passwordmanagerdemo.ui.theme.backgroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title() {
    TopAppBar(
        title = {
            Text(
                text = "Password Manager", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp
                )
            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray
        )
    )


}