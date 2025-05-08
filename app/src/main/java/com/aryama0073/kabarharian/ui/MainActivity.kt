package com.aryama0073.kabarharian.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aryama0073.kabarharian.navigation.SetupNavGraph
import com.aryama0073.kabarharian.ui.theme.KabarHarianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KabarHarianTheme {
                SetupNavGraph()
            }
        }
    }
}
