package no.uio.ifi.in2000.malikts.oblig1.ui.palindrome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import no.uio.ifi.in2000.malikts.oblig1.Screens
import androidx.compose.ui.unit.sp


@Composable
fun PalindromeChecker(navController: NavController) {
    var inputText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Palindrome Checker: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )

        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                resultText = ""
            },
            label = { Text("Skriv et ord!") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Sjekk om det ern palindrom
        fun sjekkPalindrom(ord: String){resultText = if (isPalindrome(ord)){
            "${ord.uppercase()} er en palindrom! "
        } else {
            "${ord.uppercase()} er ikke en palindrom "
        }

        }


        Text(
            text = resultText,
            fontSize = 20.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Column {
            Button(
                onClick = {
                    sjekkPalindrom(inputText)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
                    .height(50.dp)

            ) {
                Text(text = "Sjekk", fontSize = 20.sp, lineHeight = 20.sp)
            }

        }

        Spacer(modifier = Modifier.height(406.dp))

        Button(
            onClick = { navController.navigate(Screens.UnitConverterScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)

        ) {
            Text(text = "Gå til Unit Converter", fontSize = 22.sp, lineHeight = 22.sp)
        }
    }
}




