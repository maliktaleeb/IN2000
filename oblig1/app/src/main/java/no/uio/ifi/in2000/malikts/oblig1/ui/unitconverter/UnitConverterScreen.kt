package no.uio.ifi.in2000.malikts.oblig1.ui.unitconverter


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import no.uio.ifi.in2000.malikts.oblig1.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(navController: NavController) {
    var result by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") }

    val unitOptions = ConverterUnits.entries.map { it.name.lowercase() }
    var selectedUnitText by remember { mutableStateOf(unitOptions[0]) }

    fun convertAndUpdateText(input: String?, selectedUnitText: String) {
        if (input.isNullOrBlank()) {
            result = ""
        } else {
            result = try {
                val inputValue = input.toInt()
                if (inputValue > Int.MAX_VALUE / 10) {
                    "Tallet er for stort "
                } else {
                    converter(
                        inputValue,
                        ConverterUnits.valueOf(selectedUnitText.uppercase())
                    ).toString()
                }
            } catch (e: NumberFormatException) {
                "----"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Unit Converter: ",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 0.dp, bottom = 0.dp)
        )
        Column(
            modifier = Modifier
                .weight(10f)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clickable {
                        println(userInput)
                        convertAndUpdateText(userInput, selectedUnitText)
                    },
                verticalAlignment = Alignment.Bottom
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedTextField(
                    singleLine = true,
                    value = userInput,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInput = it
                        }
                    },
                    label = { Text("Skriv antall $selectedUnitText" + "s") },
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .padding(0.dp),
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            convertAndUpdateText(userInput, selectedUnitText)
                        }
                    )
                )

                Button(
                    modifier = Modifier

                        .weight(1f)
                        .fillMaxHeight()
                        .padding(
                            top = 8.dp,
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 10.dp
                        )
                        .align(Alignment.Bottom),
                    onClick = {
                        convertAndUpdateText(userInput, selectedUnitText)


                    }
                ) {
                    Text(text = "Sjekk")
                }
            }

            Spacer(Modifier.height(10.dp))

            var isExpanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it },
            ) {
                TextField(
                    readOnly = true,
                    shape = RoundedCornerShape(10.dp),
                    value = selectedUnitText,
                    onValueChange = {selectedUnitText = it},
                    label = { Text("Velg enhet") },
                    trailingIcon = {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                ) {
                    unitOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption) },
                            onClick = {
                                selectedUnitText = selectionOption
                                isExpanded = false
                                convertAndUpdateText(userInput, selectedUnitText)
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .background(Color.Blue.copy(alpha = 0.4f), shape = RoundedCornerShape(10.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        textAlign = TextAlign.Center,
                        text = "$userInput $selectedUnitText" + "s =",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                    )

                    Text(
                        text = "$result Liter",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )
                }
            }


        }

        Spacer(Modifier.height(10.dp))


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            onClick = { navController.navigate(Screens.PalindromScreen.route) },
        ) {
            Text(text = "Gå til Palindrome Checker", fontSize = 20.sp, lineHeight = 20.sp)
        }
    }

}



