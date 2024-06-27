package com.example.passwordmanagerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanagerdemo.screen.HomeScreen
import com.example.passwordmanagerdemo.screen.HomeScreenContent
import com.example.passwordmanagerdemo.ui.theme.PasswordManagerDemoTheme
import com.example.passwordmanagerdemo.viewmodel.HomeViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerDemoTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    val viewmodel: HomeViewmodel = hiltViewModel()
                    HomeScreen(modifier = Modifier, viewmodel)
                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                       }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}