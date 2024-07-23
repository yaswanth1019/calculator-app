package com.example.calculator.ui.data

import com.example.calculator.ui.screens.CalculatorViewModel

fun getOnClickAction(button: CalculatorButtonsData, calculatorViewModel: CalculatorViewModel): () -> Unit {
    return when (button) {
        CalculatorButtonsData.AC -> { { calculatorViewModel.onClear() } }
        CalculatorButtonsData.BACK -> { { calculatorViewModel.onBackSign() } }
        CalculatorButtonsData.PERCENT -> { { calculatorViewModel.onOperatorInput("%") } }
        CalculatorButtonsData.DIVIDE -> { { calculatorViewModel.onOperatorInput("÷") } }
        CalculatorButtonsData.MULTIPLY -> { { calculatorViewModel.onOperatorInput("x") } }
        CalculatorButtonsData.MINUS -> { { calculatorViewModel.onOperatorInput("-") } }
        CalculatorButtonsData.PLUS -> { { calculatorViewModel.onOperatorInput("+") } }
        CalculatorButtonsData.DOT->{{calculatorViewModel.onOperatorInput(".")}}
        CalculatorButtonsData.EQUALS -> { { calculatorViewModel.onCalculateResult() } }
        else -> { { calculatorViewModel.onDigitInput(button.symbol) } }
    }
}
