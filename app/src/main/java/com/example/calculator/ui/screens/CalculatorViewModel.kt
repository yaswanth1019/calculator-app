package com.example.calculator.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Stack
import kotlin.math.pow
import kotlin.math.round

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    init {
        resetCalc()
    }

    private fun resetCalc() {
        _uiState.value = CalculatorUiState(currentExp = "", result = 0.0)
    }

    fun onClear() {
        resetCalc()
    }

    fun onBackSign() {
        _uiState.update { currentState ->
            val newStr = if (currentState.currentExp.isNotEmpty()) {
                currentState.currentExp.dropLast(1)
            } else {
                currentState.currentExp
            }

            currentState.copy(currentExp = newStr)
        }
    }

    fun onPercentage() {
    }

    fun onOperatorInput(operator: String) {
        _uiState.update { currentState ->
            var newStr = currentState.currentExp
            if (newStr.isNotEmpty() && !newStr.last().isDigit()) {
                // If the last character is not a digit (i.e., it's an operator), replace it
                newStr = newStr.dropLast(1)
            }

            // Append the operator only if the current expression is not empty
            if (newStr.isNotEmpty()) {
                newStr += operator
            }

            currentState.copy(currentExp = newStr)
        }
    }

    fun onCalculateResult() {
        var expression = _uiState.value.currentExp
        if (expression.last() in "+-x÷%") {
            expression = expression.dropLast(1)
        }
        val result = evaluateExpression(expression)
        _uiState.update { currentState ->
            currentState.copy(result = result, currentExp = expression)
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val tokens = tokenize(expression)
        if (tokens.size < 3) {
            return 0.0
        }
        val postfix = infixToPostfix(tokens)

        return roundToDecimals(evaluatePostfix(postfix), 10) // Adjust the precision as needed
    }

    private fun tokenize(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        val number = StringBuilder()
        var hasDecimal = false

        for (char in expression) {
            when {
                char.isDigit() -> number.append(char)  // Build the number string
                char == '.' -> {
                    if (!hasDecimal) {
                        number.append(char)
                        hasDecimal = true
                    } else {
                        // Handle error for multiple decimal points in one number
                        throw IllegalArgumentException("Invalid number format: multiple decimal points.")
                    }
                }
                char in "+-x÷%" -> {
                    if (number.isNotEmpty()) {
                        tokens.add(number.toString())
                        number.clear()
                        hasDecimal = false
                    }
                    if (char == 'x') {
                        tokens.add("*")
                    } else {
                        tokens.add(char.toString())
                    }
                }
            }
        }

        if (number.isNotEmpty()) {
            tokens.add(number.toString())
        }

        return tokens
    }

    private fun infixToPostfix(tokens: List<String>): List<String> {
        val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "÷" to 2, "%" to 2)
        val output = mutableListOf<String>()
        val operators = Stack<String>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)  // Handle numbers
                token in precedence -> {
                    while (operators.isNotEmpty() && (precedence[operators.peek()]
                            ?: 0) >= (precedence[token] ?: 0)
                    ) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }

        return output
    }

    private fun evaluatePostfix(postfix: List<String>): Double {
        val stack = Stack<Double>()

        for (token in postfix) {
            when {
                token.toDoubleOrNull() != null -> stack.push(token.toDouble())  // Handle numbers
                token in "+-*÷%" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "÷" -> a / b
                        "%" -> a % b
                        else -> 0.0
                    }
                    stack.push(result)
                }
            }
        }

        return stack.pop()
    }

    private fun roundToDecimals(value: Double, decimals: Int): Double {
        val factor = 10.0.pow(decimals.toDouble())
        return round(value * factor) / factor
    }

    fun onDigitInput(button: String) {
        _uiState.update { currentState ->
            val newStr = if (currentState.currentExp == currentState.result.toString()) {
                ""
            } else {
                currentState.currentExp
            }
            currentState.copy(currentExp = newStr + button)
        }
    }
}
