package com.example.unitconverter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iConversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertTheUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?: 0.00
        val result = (inputValueDouble * iConversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
            convertTheUnits()
        },
            label = { Text(text = "Enter Value",)},
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {
                    iExpanded = true
                }) {
                    Text(inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        iConversionFactor.value = 0.01
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        iConversionFactor.value = 1.0
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        iConversionFactor.value = 0.3048
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeters"
                        iConversionFactor.value = 0.001
                        convertTheUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Button(onClick = {oExpanded = true}) {
                    Text(outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.value = 0.01
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.01
                        convertTheUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        oConversionFactor.value = 0.01
                        convertTheUnits()
                    })
                }
            }
        }
        Text(text = "Result:$outputValue $outputUnit",
            style = MaterialTheme.typography.titleMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}