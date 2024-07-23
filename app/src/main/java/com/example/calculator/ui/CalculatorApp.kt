package com.example.calculator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.screens.CalculatorScreen
import com.example.calculator.ui.screens.CalculatorViewModel

@Composable
fun CalculatorApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val calculatorViewModel:CalculatorViewModel = viewModel()

        CalculatorScreen(
            calculatorViewModel=calculatorViewModel,
            modifier=Modifier.fillMaxSize()
        )
    }
}