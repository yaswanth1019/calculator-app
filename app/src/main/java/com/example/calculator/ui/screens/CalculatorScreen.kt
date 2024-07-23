package com.example.calculator.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.data.CalculatorButtonsData
import com.example.calculator.ui.data.getOnClickAction

@Composable
fun CalculatorScreen(
    calculatorViewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    val calculatorUiState by calculatorViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        DisplayScreen(calculatorUiState=calculatorUiState)
        ButtonScreen(calculatorViewModel)
    }
}

@Composable
fun DisplayScreen(calculatorUiState: CalculatorUiState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp)
            .padding(start = 22.dp, end = 22.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column {
            Text(
                text = calculatorUiState.currentExp,
                textAlign = TextAlign.End,
                lineHeight = 45.sp,
                fontSize = 38.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
            )

            Text(text = calculatorUiState.result.toString(),
                textAlign = TextAlign.End,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
            )
        }

    }
}

@Composable
fun ButtonScreen(calculatorViewModel: CalculatorViewModel) {
    val buttonRows = CalculatorButtonsData.getButtonRows()
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        buttonRows.forEach { row ->
            Spacer(modifier = Modifier.height(5.dp))
            ButtonRow(row, calculatorViewModel)
        }
    }
}

@Composable
fun ButtonRow(buttons: List<CalculatorButtonsData>, calculatorViewModel: CalculatorViewModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        buttons.forEach { button ->
            val onClickAction = getOnClickAction(button, calculatorViewModel)
            val modifier = if (button == CalculatorButtonsData.ZERO) Modifier.weight(2f) else Modifier.weight(1f)
            AnimatedButton(
                text = button.symbol,
                color = button.color.color,
                modifier = modifier,
                onClick = onClickAction
            )
        }
    }
}




@Composable
fun AnimatedButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val cornerRadius by animateDpAsState(
        targetValue = if (isPressed) 10.dp else 50.dp,
        label = ""
    )
    Box(
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(cornerRadius))
            .size(70.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick
            ) ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = if (text == "0") TextAlign.Start else TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .then(if (text == "0") Modifier.offset(x = 27.dp) else modifier)
        )
    }
}

