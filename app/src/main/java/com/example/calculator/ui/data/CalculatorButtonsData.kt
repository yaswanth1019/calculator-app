package com.example.calculator.ui.data

import androidx.compose.ui.graphics.Color

// enums for buttons and colors

enum class CalculatorButtonsData(val symbol: String, val color: ButtonColor) {
    AC("AC", ButtonColor.FUNCTION),
    BACK("⌫", ButtonColor.FUNCTION),
    PERCENT("%", ButtonColor.FUNCTION),
    DIVIDE("÷", ButtonColor.OPERATOR),
    SEVEN("7", ButtonColor.NUMBER),
    EIGHT("8", ButtonColor.NUMBER),
    NINE("9", ButtonColor.NUMBER),
    MULTIPLY("×", ButtonColor.OPERATOR),
    FOUR("4", ButtonColor.NUMBER),
    FIVE("5", ButtonColor.NUMBER),
    SIX("6", ButtonColor.NUMBER),
    MINUS("-", ButtonColor.OPERATOR),
    ONE("1", ButtonColor.NUMBER),
    TWO("2", ButtonColor.NUMBER),
    THREE("3", ButtonColor.NUMBER),
    PLUS("+", ButtonColor.OPERATOR),
    ZERO("0", ButtonColor.NUMBER),
    DOT(".", ButtonColor.NUMBER),
    EQUALS("=", ButtonColor.OPERATOR);

    companion object {
        fun getButtonRows(): List<List<CalculatorButtonsData>> {
            return listOf(
                listOf(AC, PERCENT, BACK,DIVIDE),
                listOf(SEVEN, EIGHT, NINE, MULTIPLY),
                listOf(FOUR, FIVE, SIX, MINUS),
                listOf(ONE, TWO, THREE, PLUS),
                listOf(ZERO, DOT, EQUALS)
            )
        }
    }
}

enum class ButtonColor(val color: Color) {
    NUMBER(Color.DarkGray),
    OPERATOR(Color(0xFFFFA500)), // Orange
    FUNCTION(Color.LightGray)
}
