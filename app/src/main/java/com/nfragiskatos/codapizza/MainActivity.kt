package com.nfragiskatos.codapizza

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.nfragiskatos.codapizza.ui.AppTheme
import com.nfragiskatos.codapizza.ui.PizzaBuilderScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PizzaBuilderScreen()
            }
        }
    }
}