package com.example.calculatorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }
}
var lastChar='+';

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    val textFieldValue = remember { mutableStateOf("") }
    val result = remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = textFieldValue.value,
            onValueChange = {},
            modifier = Modifier.width(250.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { /* Perform calculation */ })
        )
        Column {
            Row {
                CalculatorButton("7", textFieldValue, result)
                CalculatorButton("8", textFieldValue, result)
                CalculatorButton("9", textFieldValue, result)
                CalculatorButton("/", textFieldValue, result)
            }
            Row {
                CalculatorButton("4", textFieldValue, result)
                CalculatorButton("5", textFieldValue, result)
                CalculatorButton("6", textFieldValue, result)
                CalculatorButton("*", textFieldValue, result)
            }
            Row {
                CalculatorButton("1", textFieldValue, result)
                CalculatorButton("2", textFieldValue, result)
                CalculatorButton("3", textFieldValue, result)
                CalculatorButton("-", textFieldValue, result)
            }
            Row {
                CalculatorButton("0", textFieldValue, result)
                CalculatorButton(".", textFieldValue, result)
                CalculatorButton("=", textFieldValue, result)
                CalculatorButton("+", textFieldValue, result)
                CalculatorButton("C", textFieldValue, result)
            }
        }
    }
}
@Composable
fun CalculatorButton(text: String, textFieldValue: MutableState<String>, result: MutableState<Double>) {
    Button(
        onClick = {

            when(text){
                "=" -> {
                    try {
                        when(lastChar){
                            '+'->
                            {
                                result.value+=textFieldValue.value.toDouble()
                                textFieldValue.value = ""
                            }
                            '-'->
                            {
                                result.value-=textFieldValue.value.toDouble()
                                textFieldValue.value = ""
                            }
                            '/'->
                            {
                                result.value/=textFieldValue.value.toDouble()
                                textFieldValue.value = ""
                            }
                            '*'->
                            {
                                result.value*=textFieldValue.value.toDouble()
                                textFieldValue.value = ""
                            }
                        }

                        textFieldValue.value = result.value.toString()
                        lastChar='='
                    } catch (e: Exception) {
                        textFieldValue.value = "Error"
                    }
                }
                "+"->
                {
                    if (lastChar=='='){
                        textFieldValue.value = ""

                    }
                    else{
                        result.value+=textFieldValue.value.toDouble()
                        textFieldValue.value = ""
                    }
                    lastChar='+'
                }
                "-"->
                {
                    if (lastChar=='='){
                        textFieldValue.value = ""
                    }
                    else{
                        result.value-=textFieldValue.value.toDouble()
                        textFieldValue.value = ""
                    }
                    lastChar='-'
                }
                "/"->
                {
                    if (lastChar=='='){
                        textFieldValue.value = ""
                    }
                    else{
                        result.value/=textFieldValue.value.toDouble()
                        textFieldValue.value = ""
                    }
                    lastChar='/'
                }
                "*"->
                {
                    if (lastChar=='='){
                        textFieldValue.value = ""
                    }
                    else {
                        result.value *= textFieldValue.value.toDouble()
                        textFieldValue.value = ""
                    }
                    lastChar = '*'
                }
                "C"->
                {
                    lastChar='C'
                    textFieldValue.value = ""
                    result.value=0.0
                }
                else -> textFieldValue.value += text

                  }
            Log.i("CLick log",result.value.toString())
            Log.i("CLick log",lastChar.toString())
                  },
        modifier = Modifier.size(64.dp)
    ) {
        Text(text = text)
    }
}
