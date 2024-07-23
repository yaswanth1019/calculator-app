# About my first project on GitHub

# Calculator App

This is a simple calculator app built using Android's ViewModel architecture. It supports basic arithmetic operations including addition, subtraction, multiplication, division, and percentage calculations.

## Features

- Basic arithmetic operations: Addition, Subtraction, Multiplication, and Division
- Percentage calculation
- Clear input
- Delete last character
- Result calculation with precision

## Getting Started

### Prerequisites

- Android Studio

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/calculator-app.git
    ```
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.

## Usage

1. Enter numbers using the digit buttons.
2. Use the operator buttons (`+`, `-`, `x`, `÷`, `%`) to perform calculations.
3. Press `=` to calculate the result.
4. Use `C` to clear the current expression and result.
5. Use `←` to delete the last character of the current expression.

## Code Overview

### `CalculatorViewModel`

- Manages the calculator's state and operations.
- Functions:
  - `onClear()`: Resets the current expression and result.
  - `onBackSign()`: Deletes the last character of the current expression.
  - `onPercentage()`: Appends the percentage operator to the current expression.
  - `onOperatorInput(operator: String)`: Handles operator input.
  - `onCalculateResult()`: Evaluates the current expression and updates the result.
  - `onDigitInput(button: String)`: Appends a digit to the current expression.
  - `evaluateExpression(expression: String)`: Tokenizes the expression, converts it to postfix notation, and evaluates it.

