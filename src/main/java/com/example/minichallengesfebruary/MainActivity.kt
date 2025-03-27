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
import androidx.compose.ui.unit.dp
import com.example.minichallengesfebruary.ui.theme.MiniChallengesFebruaryTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesFebruaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
//                    #1 Options picker
//                    OptionPicker(modifier = Modifier.padding(padding))

//                    #2 Battery indicator
                    BatteryIndicator(modifier = Modifier.padding(padding))

                }
            }
        }
    }
}
