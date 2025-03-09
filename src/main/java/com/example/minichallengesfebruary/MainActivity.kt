package com.example.minichallengesfebruary

import OptionPicker
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.minichallengesfebruary.ui.theme.MiniChallengesFebruaryTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesFebruaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    var selectedOption by remember { mutableStateOf("1,000") }
                    val options = listOf("1,000", "1.000", "1 000")

                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        Text(text = "Thousands separator")
                        OptionPicker(options = options, selectedOption = selectedOption) {
                            selectedOption = it
                        }
                    }
                }
            }
        }
    }
}
