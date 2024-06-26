package com.example.passwordmanagerdemo.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.passwordmanagerdemo.R
import com.example.passwordmanagerdemo.ui.theme.btnColor

@Composable
fun AddAccount(onFabClick:  () -> Unit) {
    FloatingActionButton(
        onClick = {
            onFabClick.invoke()
        },
        modifier = Modifier.size(60.dp),
        containerColor = btnColor
    ) {
        Icon(
            painter = painterResource(id = R.drawable.union), contentDescription = "Add",
            tint = Color.White,
            modifier = Modifier.padding(5.dp)
        )

    }
}